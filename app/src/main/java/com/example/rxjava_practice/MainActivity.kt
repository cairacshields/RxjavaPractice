package com.example.rxjava_practice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

//Add RXJava imports
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Basic observable

        //First we start by creating an observable using Observable.create()
        val observable = Observable.create(ObservableOnSubscribe<Int> {
            //Use onNext to emit each item in the stream//
            it.onNext(1)
            it.onNext(2)
            it.onNext(3)
            it.onNext(4)

            //Once the Observable has emitted all items in the sequence, call onComplete//
            it.onComplete()
        })


        //Now, let's create an Observers to subscribe to our Observables
            //Remember, the observable will not emit anything, until it has an Observer subscribed to it...
        val observer = object : Observer<Int> {
            override fun onComplete() {
                Log.e(TAG, "onComplete: All Done!")
            }

            override fun onSubscribe(d: Disposable?) {
                Log.e(TAG, "onSubscribe: ")
            }

            override fun onNext(t: Int?) {
                Log.e(TAG, "onNext: $t")
            }

            override fun onError(e: Throwable?) {
                Log.e(TAG, "onError: ")
            }

        }

        //Finally, Create our subscription//
        observable.subscribe(observer)


        //Other quicker ways to create Observables

            //Using .just()
            //This will turn any object  into an Observable...
        val objectToObservable = Observable.just("I will become an observable")

            //Using .from()
            //There are many variations to .from (ex: .fromIterable() .fromArray() .fromCallable())
            //This essentially allows us to create an Observable from the declared types

        val arrayToObservable = Observable.fromArray(arrayListOf(1,2,3,4))


        // Remember, we need to subscribe to the newly created Observables in order to actually get their values 👆

    }



    companion object {
        const val TAG = "MAIN_ACTIVITY"
    }
}
