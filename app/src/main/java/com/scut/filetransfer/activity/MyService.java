package com.scut.filetransfer.activity;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;


public class MyService extends IntentService {
    public MyService() {
        super("MyService");

    }


    @Override
    protected void onHandleIntent(Intent intent) {
        //根据Intent的不同进行不同的事务处理
        String taskName = intent.getExtras().getString("taskName");
        switch (taskName) {
            case "task1":
                Log.i("myIntentService", "do task1");
                break;
            case "task2":
                Log.i("myIntentService", "do task2");
                MyTask t= new MyTask();
                t.execute();//这里没有参数
                break;
            default:
                break;
        }
    }
    public class MyTask extends AsyncTask<String, Integer, String> {
        //执行的第一个方法用于在执行后台任务前做一些UI操作
        @Override
        protected void onPreExecute() {

        }

        //第二个执行方法,在onPreExecute()后执行，用于后台任务,不可在此方法内修改UI
        @Override
        protected String doInBackground(String... params) {
            //处理耗时操作
            return "后台任务执行完毕";
        }

        /*这个函数在doInBackground调用publishProgress(int i)时触发，虽然调用时只有一个参数
         但是这里取到的是一个数组,所以要用progesss[0]来取值
         第n个参数就用progress[n]来取值   */
        @Override
        protected void onProgressUpdate(Integer... progresses) {
            //"loading..." + progresses[0] + "%"
            super.onProgressUpdate(progresses);
        }

        /*doInBackground返回时触发，换句话说，就是doInBackground执行完后触发
        这里的result就是上面doInBackground执行后的返回值，所以这里是"后台任务执行完毕"  */
        @Override
        protected void onPostExecute(String result) {

        }

        //onCancelled方法用于在取消执行中的任务时更改UI
        @Override
        protected void onCancelled() {

        }
    }

}
