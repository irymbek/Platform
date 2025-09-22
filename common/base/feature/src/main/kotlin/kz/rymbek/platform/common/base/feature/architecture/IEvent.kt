package kz.rymbek.platform.common.base.feature.architecture

interface IEvent {
    interface Update : IEvent
    interface Action : IEvent
    interface Navigation : IEvent
}