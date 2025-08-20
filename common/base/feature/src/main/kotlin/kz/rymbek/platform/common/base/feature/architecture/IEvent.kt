package kz.rymbek.platform.common.base.feature.architecture

interface IEvent {
    interface Action : IEvent
    interface Update : IEvent
}