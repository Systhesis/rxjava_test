package com.neil.rxjava_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.neil.rxjava_test.utils.DataUtils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.observers.ConsumerSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test01();

        //test02();

        //test03();

        //test04();

        test07();
    }

    /**
     * 使用Observable.create()方法来创建一个Observable，
     * 并为他定义事件触发规则
     * @name test01
     * @author nan2.zhong
     * @date 2/20/21 8:10 PM
     []
     * @return void
     */
    public void test01() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("hello world");
                emitter.onNext("test01");
                emitter.onComplete();
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "被观察者---》观察者发送的数据 ：onNext: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: 观察者执行onComplete");
            }
        };

        observable.subscribe(observer);
    }
    
    /**
     * Observable使用from()方法创建
     * from()快捷创建事件队列，可以把一个数组或者Iterable拆分成具体对象之后，
     * 依次发送出来
     * @name test02
     * @author nan2.zhong
     * @date 2/20/21 8:23 PM 
     []
     * @return void
     */
    public void test02(){
        String[] array = new String[]{"01","02","03","04"};
        Observable<String> observable = Observable.fromArray(array);
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "被观察者---》观察者发送的数据 ：onNext: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: 观察者执行onComplete");
            }
        };

        observable.subscribe(observer);
    }

    /**
     * Observable使用just()方法创建
     * just()快捷创建事件队列
     * @name test03
     * @author nan2.zhong
     * @date 2/20/21 8:29 PM 
     []
     * @return void
     */
    public void test03(){
        Observable<String> observable = Observable.just("01","02","03","04");
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                Log.i(TAG, "被观察者---》观察者发送的数据 ：onNext: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: 观察者执行onComplete");
            }
        };

        observable.subscribe(observer);
    }

    /**
     * subscribe()不完整定义的回掉
     * @name test04
     * @author nan2.zhong
     * @date 2/20/21 8:34 PM
     []
     * @return void
     */
    public void test04() {
        //被观察者observable
        Observable<String> observable = Observable.just("01","02","03","04");

        //观察者
        Consumer<String> onNextConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.i(TAG, "onNextConsumer accept: " + s);
            }
        };

        Consumer<Throwable> onErrorConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                Log.i(TAG, "onErrorConsumer accept: " + throwable.getMessage());
            }
        };

        BiConsumer<String, String> biConsumer = new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) throws Throwable {

            }
        };

        Action onCompleteAction = new Action() {
            @Override
            public void run() throws Throwable {
                Log.i(TAG, "run: onComplete");
            }
        };

        Disposable disposable = observable.subscribe(onNextConsumer, onErrorConsumer, onCompleteAction);

    }

    /**
     * Schedulers 调度
     * @name test07
     * @author nan2.zhong
     * @date 2/25/21 4:19 PM
     []
     * @return void
     */
    public void test07() {
        Observable.fromArray(DataUtils.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Throwable {
                        Log.i(TAG, "test07: " + integers);
                    }
                });
    }


}