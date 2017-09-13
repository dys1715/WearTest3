package winsion.weartest3.api;

import java.io.File;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dys on 2017/9/12 0012.
 */
public interface IconApi {

    /**
     * 获取图片
     * http://172.16.6.29:9222/file/getFile?fileUrl=E:\du\3-face\alarmSystem\alarm-images\2017-09-12\0\0\0\1505184471385\1.jpg
     *
     * @param fileUrl
     * @return
     */
//    @GET("/file/getFile")
//    Observable<File> getFile(
//            @Query("fileUrl") String fileUrl
//    );
}
