package com.mayi.douyu.util.rx;

import android.util.Log;

import com.mayi.douyu.entity.DYResponse;
import com.mayi.douyu.entity.HYResponse;
import com.mayi.douyu.entity.PDResponse;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者 by yugai 时间 16/11/24.
 * ＊ 邮箱 784787081@qq.com
 */

public class RxResultHelper {
    private static final String TAG = "RxResultHelper";
    public static <T> Observable.Transformer<DYResponse<T>, T> douyuResult() {
        return new Observable.Transformer<DYResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<DYResponse<T>> apiResponseObservable) {
                return apiResponseObservable.flatMap(
                        new Func1<DYResponse<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(DYResponse<T> mDYResponse) {
                                Log.d(TAG, "call() called with: mDYResponse = [" + mDYResponse + "]");
                                if (mDYResponse.success()){
                                    return createData(mDYResponse.data);
                                }else{
                                    return Observable.error(new RuntimeException(mDYResponse.msg));
                                }
                            }
                        }
                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> Observable.Transformer<PDResponse<T>, T> pandaResult() {
        return new Observable.Transformer<PDResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<PDResponse<T>> apiResponseObservable) {
                return apiResponseObservable.flatMap(
                        new Func1<PDResponse<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(PDResponse<T> pdResponse) {
                                Log.d(TAG, "call() called with: mDYResponse = [" + pdResponse + "]");
                                if (pdResponse.success()){
                                    return createData(pdResponse.getData());
                                }else{
                                    return Observable.error(new RuntimeException(pdResponse.errmsg));
                                }
                            }
                        }
                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Observable.Transformer<HYResponse<T>,T> huyaResult(){
        return new Observable.Transformer<HYResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<HYResponse<T>> hyResponseObservable) {
                return hyResponseObservable.flatMap(
                        new Func1<HYResponse<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(HYResponse<T> thyResponse) {
                                if (thyResponse.success()){
                                    return createData(thyResponse.getData());
                                }else{
                                    return Observable.error(new RuntimeException(thyResponse.getMessage()));
                                }
                            }
                        }
                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    private static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
