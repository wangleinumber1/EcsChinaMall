package com.jiajie.jiajieproject.db.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class CityDataService {

	public static String TABLENAME = "web_note";
	private SQLiteDatabase db;

	// 数据库存储路径
	String filePath = "data/data/com.jiajie.jiajieproject/city.db";
	// 数据库存放的文件夹 data/data/com.main.jh 下面
	String pathStr = "data/data/com.jiajie.jiajieproject";
	private Cursor rawQuery;
	int i = 1;
	private Cursor rawQuery2;
	private Cursor rawQuery3;
	private Cursor rawQuery5;
	private Cursor rawQuery4;
	private Cursor rawQuery6;

	public CityDataService(Context context) {

		db = openDatabase(context);

	}

	public SQLiteDatabase openDatabase(Context context) {
		System.out.println("filePath:" + filePath);
		File jhPath = new File(filePath);
		// 查看数据库文件是否存在
		if (jhPath.exists()) {
			Log.i("test", "存在数据库");
			// 存在则直接返回打开的数据库
			return SQLiteDatabase.openOrCreateDatabase(jhPath, null);
		} else {
			// 不存在先创建文件夹
			File path = new File(pathStr);
			Log.e("test", "pathStr=" + path);
			if (path.mkdir()) {
				Log.e("test", "创建成功");
			} else {
				Log.e("test", "创建失败");
			}
			;
			try {
				// 得到资源
				AssetManager am = context.getAssets();
				// 得到数据库的输入流
				InputStream is = am.open("china_city");
				Log.e("test", is + "");
				// 用输出流写到SDcard上面
				FileOutputStream fos = new FileOutputStream(jhPath);
				Log.e("test", "fos=" + fos);
				Log.e("test", "jhPath=" + jhPath);
				// 创建byte数组 用于1KB写一次
				byte[] buffer = new byte[1024];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					Log.e("test", "得到" + i);
					i++;
					fos.write(buffer, 0, count);
				}
				// 最后关闭就可以了
				fos.flush();
				fos.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			// 如果没有这个数据库 我们已经把他写到SD卡上了，然后在执行一次这个方法 就可以返回数据库了
			return openDatabase(context);
		}
	}

	// 获取科目的条数
	public int getQueCount(int kemu) {
		rawQuery2 = db.rawQuery("select count(*) from " + TABLENAME
				+ " where kemu=?", new String[] { kemu + "" });
		try {

			if (rawQuery2.moveToFirst()) {
				return rawQuery2.getInt(0);
			}

		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	// 修改数据
	public void setExamAnsChoose(String examAnsChoose, String intNumber) {
		String sql = "update " + TABLENAME + " set examAnsChoose='"
				+ examAnsChoose + "' where intNumber='" + intNumber + "'";
		db.execSQL(sql);
	}

	// 修改数据
	public void setExamAnsState(String examAnsState, String intNumber) {
		String sql = "update " + TABLENAME + " set examAnsState='"
				+ examAnsState + "' where intNumber='" + intNumber + "'";
		db.execSQL(sql);
	}

	// 放弃考试修改数据
	public void setExamState(String examAnsState, String examAnsChoose) {
		String sql = "update " + TABLENAME
				+ " set examAnsState=?,examAnsChoose=?";
		db.execSQL(sql, new String[] { examAnsState, examAnsChoose });
	}

	// 获取考试答题的条数
	public int getExamCount(int kemu, int trues) {
		rawQuery4 = db.rawQuery("select count(*) from " + TABLENAME
				+ " where kemu=? and examAnsState=?", new String[] { kemu + "",
				trues + "" });
		try {

			if (rawQuery4.moveToFirst()) {
				return rawQuery4.getInt(0);
			}

		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	public void close() {
		if (rawQuery != null) {
			rawQuery.close();
		}
		if (rawQuery2 != null) {
			rawQuery2.close();
		}
		if (rawQuery3 != null) {
			rawQuery3.close();
		}
		if (rawQuery4 != null) {
			rawQuery4.close();
		}

		if (rawQuery5 != null) {
			rawQuery5.close();
		}
		if (rawQuery6 != null) {
			rawQuery6.close();
		}
		if (db != null) {
			db.close();
		}
	}

}
