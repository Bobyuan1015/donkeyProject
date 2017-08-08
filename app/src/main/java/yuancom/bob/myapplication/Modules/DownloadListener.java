package yuancom.bob.myapplication.Modules;

/**
 * Created by bob on 07/08/2017.
 */

public interface DownloadListener {
    void onStartDownload();
    void onFinishDownload(ResponseElements responseElements);

}
