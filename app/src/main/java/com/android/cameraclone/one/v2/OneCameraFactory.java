/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.android.cameraclone.one.v2;

import com.android.cameraclone.FatalErrorHandler;
import com.android.cameraclone.async.MainThread;
import com.android.cameraclone.async.Observable;
import com.android.cameraclone.burst.BurstFacade;
import com.android.cameraclone.one.OneCamera;
import com.android.cameraclone.one.OneCameraCharacteristics;
import com.android.cameraclone.one.config.OneCameraFeatureConfig.CaptureSupportLevel;
import com.android.cameraclone.one.v2.camera2proxy.CameraDeviceProxy;
import com.android.cameraclone.one.v2.imagesaver.ImageSaver;
import com.android.cameraclone.util.Size;

public interface OneCameraFactory {
    OneCamera createOneCamera(CameraDeviceProxy cameraDevice,
            OneCameraCharacteristics characteristics,
            CaptureSupportLevel supportLevel,
            MainThread mainThread,
            Size pictureSize,
            ImageSaver.Builder imageSaverBuilder,
            Observable<OneCamera.PhotoCaptureParameters.Flash> flashSetting,
            Observable<Integer> exposureSetting,
            Observable<Boolean> hdrSceneSetting,
            BurstFacade burstController,
            FatalErrorHandler fatalErrorHandler);
}
