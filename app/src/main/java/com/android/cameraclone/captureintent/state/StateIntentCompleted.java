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

package com.android.cameraclone.captureintent.state;

import com.google.common.base.Optional;

import com.android.cameraclone.app.AppController;
import com.android.cameraclone.async.RefCountBase;
import com.android.cameraclone.captureintent.resource.ResourceConstructed;
import com.android.cameraclone.captureintent.stateful.State;
import com.android.cameraclone.captureintent.stateful.StateImpl;

import android.content.Intent;

/**
 *
 */
public final class StateIntentCompleted extends StateImpl {
    private final RefCountBase<ResourceConstructed> mResourceConstructed;
    private final Optional<Intent> mResultIntent;

    public static StateIntentCompleted from(
            StateSavingPicture savingPicture,
            RefCountBase<ResourceConstructed> resourceConstructed,
            Intent resultIntent) {
        return new StateIntentCompleted(
                savingPicture, resourceConstructed, Optional.of(resultIntent));
    }

    public static StateIntentCompleted from(
            State previousState,
            RefCountBase<ResourceConstructed> resourceConstructed) {
        return new StateIntentCompleted(
                previousState, resourceConstructed, Optional.<Intent>absent());
    }

    private StateIntentCompleted(
            State previousState,
            RefCountBase<ResourceConstructed> resourceConstructed,
            Optional<Intent> resultIntent) {
        super(previousState);
        mResourceConstructed = resourceConstructed;
        mResourceConstructed.addRef();
        mResultIntent = resultIntent;
    }

    @Override
    public Optional<State> onEnter() {
        final AppController appController = mResourceConstructed.get().getAppController();
        mResourceConstructed.get().getMainThread().execute(new Runnable() {
            @Override
            public void run() {
                if (mResultIntent.isPresent()) {
                    appController.finishActivityWithIntentCompleted(mResultIntent.get());
                } else {
                    appController.finishActivityWithIntentCanceled();
                }
            }
        });
        return NO_CHANGE;
    }

    @Override
    public void onLeave() {
    }
}