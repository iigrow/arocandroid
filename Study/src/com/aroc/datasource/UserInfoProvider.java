package com.aroc.datasource;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class UserInfoProvider extends ContentProvider {

	private static UriMatcher _matcherMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);

	private static final int Insert = 1;
	private static final int Delete = 2;
	private static final int Update = 3;
	private static final int Query = 4;
	static {
		_matcherMatcher.addURI("com.aroc.study.UserInfoProvider", "insert",
				Insert);
		_matcherMatcher.addURI("com.aroc.study.UserInfoProvider", "delete",
				Delete);
		_matcherMatcher.addURI("com.aroc.study.UserInfoProvider", "update",
				Update);
		_matcherMatcher.addURI("com.aroc.study.UserInfoProvider", "query",
				Query);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		if(_matcherMatcher.match(uri)==Query)
		{
			
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
