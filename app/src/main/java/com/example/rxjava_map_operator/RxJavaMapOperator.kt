package com.example.rxjava_map_operator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class RxJavaMapOperator : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getObservableWithMap()
        getFlatMapObservable()
    }

    /**
     * This observable emits the Integer and performing +2 operation on each Integer
     */
    private fun getObservableWithMap() {

        // Create a observable
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
    }

    /**
     * This Observable emits the Integer & checking emitted integer is even or Odd by calling second observable using FlatMap.
     */
    private fun getFlatMapObservable() {

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


    }

    private fun getSecondObservable(int: Int): Observable<Int> {

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

}
