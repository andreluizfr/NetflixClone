export interface ReduxAction<T> {
    payload: T,
    type: string
}