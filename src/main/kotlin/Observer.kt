interface Observer {
    fun update(dodgeSuccess: Boolean)
}

interface Subject {
    fun registerObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers(dodgeSuccess: Boolean)
}