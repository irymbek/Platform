package kz.rymbek.platform.common.base.database

interface TransactionRunner {
    suspend fun <T> runInTransaction(block: suspend () -> T): T
}