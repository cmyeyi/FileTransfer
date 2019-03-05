package com.scut.filetransfer.adapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.scut.filetransfer.R;
import com.scut.filetransfer.util.FileUtil;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;

/**
 * 适配器管理器
 *
 */
public class AdapterManager {
	private Context mContext;
	private DeviceListAdapter mDeviceListAdapter;   //设备列表 adapter
	private FileListAdapter mFileListAdapter;    //文件列表adapter
	private List<BluetoothDevice> mDeviceList;   //设备集合
	private List<File> mFileList;    //文件集合
	private Handler mainHandler;   //主线程Handler
	
	public AdapterManager(Context context){
		this.mContext = context;
	}
	
	/**
	 * 取得设备列表adapter
	 */
	public DeviceListAdapter getDeviceListAdapter(){
		if(null == mDeviceListAdapter){
			mDeviceList = new ArrayList<BluetoothDevice>();
			mDeviceListAdapter = new DeviceListAdapter(mContext, mDeviceList, R.layout.device_list_item);
		}
		
		return mDeviceListAdapter;
	}
	
	/**
	 * 取得文件列表adapter
	 */
	public FileListAdapter getFileListAdapter(){
		if(null == mFileListAdapter){
			mFileList = new ArrayList<File>();
			mFileListAdapter = new FileListAdapter(mContext, mFileList, R.layout.file_list_item);
		}
		
		return mFileListAdapter;
	}
	
	/**
	 * 更新设备列表listView
	 */
	public void updateDeviceAdapter(){
				mDeviceListAdapter.notifyDataSetChanged();
	}
	
	/**
	 * 清空设备列表
	 */
	public void clearDevice(){
		if(null != mDeviceList){
			mDeviceList.clear();
		}
	}
	
	/**
	 * 添加设备
	 */
	public void addDevice(BluetoothDevice bluetoothDevice){
		mDeviceList.add(bluetoothDevice);
	}
	
	/**
	 * 更新设备信息
	 */
	public void changeDevice(int listId, BluetoothDevice bluetoothDevice){
		mDeviceList.remove(listId);
		mDeviceList.add(listId, bluetoothDevice);
	}
	
	/**
	 * 更新文件列表
	 * @param path
	 */
	public void updateFileListAdapter(String path){
		mFileList.clear();
		mFileList.addAll(FileUtil.getFileList(path));
		if(null == mainHandler){
			mainHandler = new Handler(mContext.getMainLooper());
		}
		mainHandler.post(new Runnable() {
			
			@Override
			public void run() {
				mFileListAdapter.notifyDataSetChanged();
			}
		});
	}
	
	/**
	 * 取得设备列表
	 */
	public List<BluetoothDevice> getDeviceList() {
		return mDeviceList;
	}

}