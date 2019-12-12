#### RxJava Map & FlatMap Operators examples

A simple android demo that demonstrates RxJava Map & FlatMap operators.

#### Map Operator

This operator transforms the items emitted by an Observable by applying a function to each item. map() operator allows for us to modify the emitted item from the Observable and then emits the modified item.

```
 var intList = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        var observable = Observable.create(ObservableOnSubscribe<Int> {
            for (i in 0..intList.size) it.onNext(i)
        })
        // Subscribe the observer
        observable.map { t ->
            (t + 2)
        }.subscribe(object : Observer<Int> {
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(t: Int) {

                Log.e("onNext()", t.toString())
            }

            override fun onError(e: Throwable) {}
        })
```

#### FlatMap Operator

This operator transforms each item emitted by an Observable but instead of returning the modified item, it returns the Observable itself which can emit data again

```
       var intList = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)

        var firstObservable = Observable.create(ObservableOnSubscribe<Int> {
            for (i in 0..intList.size) it.onNext(i)
        })

        firstObservable.flatMap { t -> getSecondObservable(t) }.subscribe(object : Observer<Int> {
            override fun onComplete() {}

            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: Int) {
                Log.e("onNext()-First", if (t % 2 == 0) "Even" else "Odd")
            }

            override fun onError(e: Throwable) {}

        })
        
        fun getSecondObservable(int: Int): Observable<Int> {

        var secondObservable =
            Observable.create(ObservableOnSubscribe<Int> { emitter -> emitter.onNext(int) })
        secondObservable.subscribe(object : Observer<Int> {
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(t: Int) {
                Log.e("onNext()-Second", t.toString())
            }

            override fun onError(e: Throwable) {}
        })
        return secondObservable
    }
```

