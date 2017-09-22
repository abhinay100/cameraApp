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

package com.android.cameraclone.captureintent.resource;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.android.cameraclone.FatalErrorHandler;
import com.android.cameraclone.SoundPlayer;
import com.android.cameraclone.app.AppController;
import com.android.cameraclone.app.LocationManager;
import com.android.cameraclone.app.OrientationManager;
import com.android.cameraclone.async.MainThread;
import com.android.cameraclone.async.SafeCloseable;
import com.android.cameraclone.burst.BurstFacade;
import com.android.cameraclone.captureintent.CaptureIntentModuleUI;
import com.android.cameraclone.one.OneCameraManager;
import com.android.cameraclone.one.OneCameraOpener;
import com.android.cameraclone.settings.CameraFacingSetting;
import com.android.cameraclone.settings.ResolutionSetting;
import com.android.cameraclone.settings.SettingsManager;

/**
 * Defines an interface that any implementation of this should retain basic
 * resources to construct a state machine of
 * {@link com.android.cameraclone.captureintent.CaptureIntentModule}.
 */
public interface ResourceConstructed extends SafeCloseable {
    /**
     * Obtains the intent that starts this activity.
     *
     * @return An {@link android.content.Intent} object.
     */
    public Intent getIntent();

    /**
     * Obtains the UI object associated with the intent module.
     *
     * @return A {@link com.android.cameraclone.captureintent.CaptureIntentModuleUI}
     *         object.
     */
    public CaptureIntentModuleUI getModuleUI();

    /**
     * Obtains the scope namespace used in
     * {@link com.android.cameraclone.settings.SettingsManager}.
     *
     * @return The scope namespace of the intent module.
     */
    public String getSettingScopeNamespace();

    /**
     * Obtains the main thread.
     *
     * @return A {@link com.android.cameraclone.async.MainThread} object.
     */
    public MainThread getMainThread();

    /**
     * Obtains the Android application context.
     *
     * @return A {@link android.content.Context} object.
     */
    public Context getContext();

    /**
     * Obtains the hardware manager that provides the ability to query for
     * hardware specific characteristics.
     *
     * @return  An {@link com.android.cameraclone.one.OneCameraManager} object.
     */
    public OneCameraManager getOneCameraManager();

    /**
     * Obtains the camera manager that controls camera devices.
     *
     * @return An {@link com.android.cameraclone.one.OneCameraOpener} object.
     */
    public OneCameraOpener getOneCameraOpener();

    /**
     * Obtains the location manager that is able to report device current
     * location.
     *
     * @return A {@link com.android.cameraclone.app.LocationManager} object.
     */
    public LocationManager getLocationManager();

    /**
     * Obtains the orientation manager that is able to report device current
     * orientation.
     *
     * @return An {@link com.android.cameraclone.app.OrientationManager} object.
     */
    public OrientationManager getOrientationManager();

    /**
     * Obtains the settings manager of the activity.
     *
     * @return A {@link com.android.cameraclone.settings.SettingsManager} object.
     */
    public SettingsManager getSettingsManager();

    /**
     * Obtains the burst facade.
     *
     * @return A {@link com.android.cameraclone.burst.BurstFacade} object.
     */
    public BurstFacade getBurstFacade();

    /**
     * Obtains the current camera facing setting.
     *
     * @return A {@link com.android.cameraclone.settings.CameraFacingSetting} object.
     */
    public CameraFacingSetting getCameraFacingSetting();

    /**
     * Obtains the current resolution setting.
     *
     * @return A {@link com.android.cameraclone.settings.ResolutionSetting} object.
     */
    public ResolutionSetting getResolutionSetting();

    /**
     * Obtains a handler to perform camera related operations.
     *
     * @return A {@link android.os.Handler} object.
     */
    public Handler getCameraHandler();

    /**
     * Obtains a sound player that is able to play various capture sounds.
     *
     * @return A {@link com.android.cameraclone.SoundPlayer} object.
     */
    public SoundPlayer getSoundPlayer();

    /**
     * Obtains the app controller
     *
     * @deprecated Please avoid to use app controller.
     * @return An {@link com.android.cameraclone.app.AppController} object.
     */
    @Deprecated
    public AppController getAppController();

    /**
     * Obtains the fatal error handler.
     *
     * @return An {@link FatalErrorHandler} object.
     */
    public FatalErrorHandler getFatalErrorHandler();
}
