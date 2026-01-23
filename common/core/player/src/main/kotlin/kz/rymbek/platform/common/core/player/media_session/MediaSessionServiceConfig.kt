package kz.rymbek.platform.common.core.player.media_session

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.FileDataSource
import androidx.media3.datasource.cache.CacheDataSink
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.NoOpCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import java.io.File

@OptIn(UnstableApi::class)
object MediaSessionServiceConfig {
    fun createTrackSelector(context: Context): DefaultTrackSelector {
        return DefaultTrackSelector(context, AdaptiveTrackSelection.Factory())
    }

    fun createAudioAttributes(): AudioAttributes {
        return AudioAttributes.Builder()
            .setUsage(C.USAGE_MEDIA)
            .build()
    }

    fun createLoadControl(): DefaultLoadControl {
        return DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                50_000, // Min buffer
                50_000, // Max buffer
                2500,   // Buffer for playback start
                5000    // Buffer for playback resume
            )
            .setPrioritizeTimeOverSizeThresholds(true)
            .build()
    }

    fun createRenderersFactory(context: Context): DefaultRenderersFactory {
        return DefaultRenderersFactory(context)
            .setEnableDecoderFallback(true)
    }

    fun createCacheDataSourceFactory(context: Context): CacheDataSource.Factory {
        val downloadContentDirectory = File(context.getExternalFilesDir(null), "downloads")
        val downloadCache = SimpleCache(
            downloadContentDirectory,
            NoOpCacheEvictor(),
            StandaloneDatabaseProvider(context)
        )

        val cacheSink = CacheDataSink.Factory()
            .setCache(downloadCache)

        val upstreamFactory = DefaultDataSource.Factory(context, DefaultHttpDataSource.Factory())
        val downStreamFactory = FileDataSource.Factory()

        return CacheDataSource.Factory()
            .setCache(downloadCache)
            .setCacheWriteDataSinkFactory(cacheSink)
            .setCacheReadDataSourceFactory(downStreamFactory)
            .setUpstreamDataSourceFactory(upstreamFactory)
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
    }
}