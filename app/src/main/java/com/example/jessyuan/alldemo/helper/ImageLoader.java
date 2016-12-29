package com.example.jessyuan.alldemo.helper;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.jessyuan.alldemo.listeners.ImageLoaderListener;
import com.example.jessyuan.alldemo.model.Folder;
import com.example.jessyuan.alldemo.model.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JessYuan on 25/11/2016.
 */

@Singleton
public class ImageLoader {

    String[] columnIndexs = new String[] {
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
    };

    private Context mContext;

    private Map<String, Folder> mCacheFolders;

    private boolean mCacheDirty = false;

    @Inject
    public ImageLoader(Context context) {
        mContext = context;
    }

    public Single<List<Folder>> loadImages() {
        return Observable.concat(loadCacheImages(), loadDeviceImages())
                .first(new ArrayList<Folder>());
    }

    /**
     * clear cache, and load device images
     * @return
     */
    public Single<List<Folder>> refreshImages() {
        mCacheDirty = false;
        mCacheFolders.clear();
        return loadImages();
    }

    /**
     * load memory cache images
     * @return
     */
    private Observable<List<Folder>> loadCacheImages() {
        return Observable.create(new ObservableOnSubscribe<List<Folder>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Folder>> e) throws Exception {
                if (mCacheDirty && mCacheFolders != null && mCacheFolders.size() != 0) {
                    e.onNext(new ArrayList<Folder>(mCacheFolders.values()));
                } else {
                    e.onComplete();
                }
            }
        });
    }

    /**
     * load device images
     * @return
     */
    private Observable<List<Folder>> loadDeviceImages() {
        return new Observable<List<Folder>>() {
            @Override
            protected void subscribeActual(Observer<? super List<Folder>> observer) {
                Cursor cursor = mContext.getContentResolver().query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        columnIndexs, null, null, MediaStore.Images.Media.DATE_ADDED);

                if (cursor == null) {
                    observer.onError(new NullPointerException());
                }

                List<Image> temp = new ArrayList<>(cursor.getCount());

                mCacheDirty = true;
                if (mCacheFolders == null) {
                    mCacheFolders = new LinkedHashMap<>();
                }

                if (cursor.moveToLast()) {
                    do {
                        long id = cursor.getLong(cursor.getColumnIndex(columnIndexs[0]));
                        String name = cursor.getString(cursor.getColumnIndex(columnIndexs[1]));
                        String path = cursor.getString(cursor.getColumnIndex(columnIndexs[2]));
                        String bucket = cursor.getString(cursor.getColumnIndex(columnIndexs[3]));

                        File file = new File(path);
                        if (file.exists()) {
                            Image image = new Image(id, name, path, false);
                            temp.add(image);


                            Folder folder = mCacheFolders.get(bucket);
                            if (folder == null) {
                                folder = new Folder(bucket);
                                mCacheFolders.put(bucket, folder);
                            }

                            folder.getImages().add(image);
                        }
                    } while (cursor.moveToPrevious());
                }

                cursor.close();

                List<Folder> folders = new ArrayList<>(mCacheFolders.values());

                observer.onNext(folders);
            }
        };
    }
}
