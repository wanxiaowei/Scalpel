package com.example.scalpel;

import java.util.HashMap;
import java.util.Iterator;

import android.R.bool;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class ScalpelService extends Service {
	int VendorID, ProductID;
	final int bytelength=50;
	public static final String TAG = "ScalpelService";
	UsbDevice myUsbDevice;
	UsbInterface Interface1;
	UsbEndpoint epBulkOut, epBulkIn, epControl, epIntEndpointOut,
			epIntEndpointIn;
	UsbManager myUsbManager;
	UsbDeviceConnection myDeviceConnection;
	int index=5;
	int[] usbinf=new int[bytelength];
	public void onCreat() {
		super.onCreate();
		Log.d(TAG, "onCreate() executed");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("进入service的onStart函数");
		myUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE); // 获取UsbManager

		// 枚举设备
		enumerateDevice(myUsbManager);
		// 查找设备接口
		getDeviceInterface();
		// 获取设备endpoint
		assignEndpoint(Interface1);
		// 打开conn连接通道
		openDevice(Interface1);
		myThread m= new myThread();
		new Thread(m).start();
		return super.onStartCommand(intent, flags, startId); // 每次startService（intent）时都回调该方法
	}

	class myThread implements Runnable {
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				byte[] bt = new byte[bytelength];
				bt = receiveMessageFromPoint();
				if (bt[0] == -100)
					continue;
				/*if(index>0){
					for (int i = 0; i < bytelength; i++)
						System.out.println(bt[i]);
					index--;
				}*/
				int infn=0;
				int handid,hand = 0,vetical=0,angle = 0,force=0;
				for(int i=0;bt[i]!=0||bt[i+1]!=0;i+=2){
					usbinf[infn]=getint(bt[i],bt[i+1]);
					System.out.print(usbinf[infn]);
					infn++;
				}
				System.out.println();
				if(usbinf[0]!=0) continue;;
				if(usbinf[1]!=0) continue;
				handid=usbinf[2];
				int now=4,jz=0;
				while(now<infn){
					if(usbinf[now]==0&&usbinf[now+1]!=0){
						hand=jz;
						break;
					}
					jz=jz*10+usbinf[now];
					now++;
				}
				now++;jz=0;
				while(now<infn){
					if(usbinf[now]==0&&usbinf[now+1]!=0){
						vetical=jz;
						break;
					}
					jz=jz*10+usbinf[now];
					now++;
				}
				now++;jz=0;
				while(now<infn){
					if(usbinf[now]==0&&usbinf[now+1]!=0){
						angle=jz;
						break;
					}
					jz=jz*10+usbinf[now];
					now++;
				}
				now++;jz=0;
				while(now<infn){
					if(now==infn-2){
						force=jz;
						break;
					}
					jz=jz*10+usbinf[now];
					now++;
				}
				Communication.setHandid(handid);
				Communication.setAngle(angle);
				Communication.setForce(force);
				Communication.setVetical(vetical);
				if (Communication.getReceive() == true) {
					Communication.addnownum();
					Communication.addHand(handid,hand);
					Communication.addVetical(vetical);
					Communication.addAngle(angle);
					Communication.addForce(force);
				}
			}
		}
		public int getint(byte t1,byte t2){
			if(t1==0&&t2==-32) return 0;
			if(t1==6&&t2==-8) return 1;
			if(t1==24&&t2==-8) return 2;
			if(t1==30&&t2==-32) return 3;
			if(t1==96&&t2==-8) return 4;
			if(t1==102&&t2==-32) return 5;
			if(t1==120&&t2==-32) return 6;
			if(t1==126&&t2==-8) return 7;
			if(t1==-128&&t2==-8) return 8;
			if(t1==-122&&t2==-32) return 9;
			return -1;
		}
	}

	private void enumerateDevice(UsbManager mUsbManager) {
		System.out.println("开始进行枚举设备!");
		if (mUsbManager == null) {
			System.out.println("创建UsbManager失败，请重新启动应用！");
			return;
		} else {
			HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
			if (!(deviceList.isEmpty())) {
				// deviceList不为空
				System.out.println("deviceList is not null!");
				Iterator<UsbDevice> deviceIterator = deviceList.values()
						.iterator();
				while (deviceIterator.hasNext()) {
					UsbDevice device = deviceIterator.next();
					// 输出设备信息
					System.out.println("DeviceInfo:" + device.getVendorId()
							+ " , " + device.getProductId());
					// 保存设备VID和PID
					VendorID = device.getVendorId();
					ProductID = device.getProductId();
					// 保存匹配到的设备
					if (VendorID == 6790 && ProductID == 29987) {
						myUsbDevice = device; // 获取USBDevice
						System.out.println("发现待匹配设备:" + device.getVendorId()
								+ "," + device.getProductId());
						Context context = getApplicationContext();
						Toast.makeText(context, "发现待匹配设备", Toast.LENGTH_SHORT)
								.show();
					}
				}
			} else {
				System.out.println("failed");
				Context context = getApplicationContext();
				Toast.makeText(context, "请连接USB设备至PAD！", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	private void getDeviceInterface() {
		if (myUsbDevice != null) {
			Log.d(TAG, "interfaceCounts : " + myUsbDevice.getInterfaceCount());
			System.out.println("interfaceCounts : "
					+ myUsbDevice.getInterfaceCount());
			for (int i = 0; i < myUsbDevice.getInterfaceCount(); i++) {
				UsbInterface intf = myUsbDevice.getInterface(i);

				if (i == 0) {
					Interface1 = intf; // 保存设备接口
					System.out.println("成功获得设备接口:" + Interface1.getId());
				}
			}
		} else {
			System.out.println("设备为空！");
		}

	}

	private UsbEndpoint assignEndpoint(UsbInterface mInterface) {

		for (int i = 0; i < mInterface.getEndpointCount(); i++) {
			UsbEndpoint ep = mInterface.getEndpoint(i);
			// look for bulk endpoint
			if (ep.getType() == UsbConstants.USB_ENDPOINT_XFER_BULK) {
				if (ep.getDirection() == UsbConstants.USB_DIR_OUT) {
					epBulkOut = ep;
					System.out.println("Find the BulkEndpointOut," + "index:"
							+ i + "," + "使用端点号："
							+ epBulkOut.getEndpointNumber());
				} else {
					epBulkIn = ep;
					System.out
							.println("Find the BulkEndpointIn:" + "index:" + i
									+ "," + "使用端点号："
									+ epBulkIn.getEndpointNumber());
				}
			}
			// look for contorl endpoint
			if (ep.getType() == UsbConstants.USB_ENDPOINT_XFER_CONTROL) {
				epControl = ep;
				System.out.println("find the ControlEndPoint:" + "index:" + i
						+ "," + epControl.getEndpointNumber());
			}
			// look for interrupte endpoint
			if (ep.getType() == UsbConstants.USB_ENDPOINT_XFER_INT) {
				if (ep.getDirection() == UsbConstants.USB_DIR_OUT) {
					epIntEndpointOut = ep;
					System.out.println("find the InterruptEndpointOut:"
							+ "index:" + i + ","
							+ epIntEndpointOut.getEndpointNumber());
				}
				if (ep.getDirection() == UsbConstants.USB_DIR_IN) {
					epIntEndpointIn = ep;
					System.out.println("find the InterruptEndpointIn:"
							+ "index:" + i + ","
							+ epIntEndpointIn.getEndpointNumber());
				}
			}
		}
		if (epBulkOut == null && epBulkIn == null && epControl == null
				&& epIntEndpointOut == null && epIntEndpointIn == null) {
			throw new IllegalArgumentException("not endpoint is founded!");
		}
		return epIntEndpointIn;
	}

	public void openDevice(UsbInterface mInterface) {
		if (mInterface != null) {
			UsbDeviceConnection conn = null;
			// 在open前判断是否有连接权限；对于连接权限可以静态分配，也可以动态分配权限
			if (myUsbManager.hasPermission(myUsbDevice)) {
				conn = myUsbManager.openDevice(myUsbDevice);
			}

			if (conn == null) {
				System.out.println("no permission");
				return;
			}

			if (conn.claimInterface(mInterface, true)) {
				myDeviceConnection = conn;
				if (myDeviceConnection != null)
					System.out.println("open设备成功！");
				final String mySerial = myDeviceConnection.getSerial();
				System.out.println("设备serial number：" + mySerial);
			} else {
				System.out.println("无法打开连接通道。");
				conn.close();
			}
		}
	}

	private byte[] receiveMessageFromPoint() {
		byte[] buffer = new byte[bytelength];
		if (myDeviceConnection.bulkTransfer(epBulkIn, buffer, buffer.length,
				2000) < 0) {
			buffer[0] = -100;
			System.out.println("bulkIn返回输出为  负数");
		} else {
			System.out.println("Receive Message Succese！");
		}
		return buffer;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
