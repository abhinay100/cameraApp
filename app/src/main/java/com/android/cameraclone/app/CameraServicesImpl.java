/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.cameraclone.app;

import android.content.Context;

import com.android.cameraclone.MediaSaverImpl;
import com.android.cameraclone.Storage;
import com.android.cameraclone.async.MainThread;
import com.android.cameraclone.remote.RemoteShutterListener;
import com.android.cameraclone.session.CaptureSessionFactory;
import com.android.cameraclone.session.CaptureSessionFactoryImpl;
import com.android.cameraclone.session.CaptureSessionManager;
import com.android.cameraclone.session.CaptureSessionManagerImpl;
import com.android.cameraclone.session.PlaceholderManager;
import com.android.cameraclone.session.SessionStorageManager;
import com.android.cameraclone.session.SessionStorageManagerImpl;
import com.android.cameraclone.session.StackSaverFactory;
import com.android.cameraclone.settings.SettingsManager;
import com.android.cameraclone.util.AndroidContext;
import com.android.cameraclone.util.RemoteShutterHelper;

/**
 * Functionality available to all modules and services.
 */
public class CameraServicesImpl implements CameraServices {
    /**
     * Fast, thread safe singleton initialization.
     */
    private static class Singleton {
        private static final CameraServicesImpl INSTANCE = new CameraServicesImpl(
              AndroidContext.instance().get());
    }

    /**
     * @return a single instance of of the global camera services.
     */
    public static CameraServicesImpl instance() {
        return Singleton.INSTANCE;
    }

    private final MediaSaver mMediaSaver;
    private final CaptureSessionManager mSessionManager;
    private final MemoryManagerImpl mMemoryManager;
    private final RemoteShutterListener mRemoteShutterListener;
    private final MotionManager mMotionManager;
    private final SettingsManager mSettingsManager;

    private CameraServicesImpl(Context context) {
        mMediaSaver = new MediaSaverImpl(context.getContentResolver());
        PlaceholderManager mPlaceHolderManager = new PlaceholderManager(context);
        SessionStorageManager mSessionStorageManager = SessionStorageManagerImpl.create(context);

        StackSaverFactory mStackSaverFactory = new StackSaverFactory(Storage.DIRECTORY,
              context.getContentResolver());
        CaptureSessionFactory captureSessionFactory = new CaptureSessionFactoryImpl(
                mMediaSaver, mPlaceHolderManager, mSessionStorageManager, mStackSaverFactory);
        mSessionManager = new CaptureSessionManagerImpl(
                captureSessionFactory, mSessionStorageManager, MainThread.create());
        mMemoryManager = MemoryManagerImpl.create(context, mMediaSaver);
        mRemoteShutterListener = RemoteShutterHelper.create(context);
        mSettingsManager = new SettingsManager(context);

        mMotionManager = new MotionManager(context);
    }

    @Override
    public CaptureSessionManager getCaptureSessionManager() {
        return mSessionManager;
    }

    @Override
    public MemoryManager getMemoryManager() {
        return mMemoryManager;
    }

    @Override
    public MotionManager getMotionManager() {
        return mMotionManager;
    }

    @Override
    @Deprecated
    public MediaSaver getMediaSaver() {
        return mMediaSaver;
    }

    @Override
    public RemoteShutterListener getRemoteShutterListener() {
        return mRemoteShutterListener;
    }

    @Override
    public SettingsManager getSettingsManager() {
        return mSettingsManager;
    }
}
