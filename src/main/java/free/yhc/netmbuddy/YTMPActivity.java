/******************************************************************************
 * Copyright (C) 2012, 2013, 2014, 2015, 2016
 * Younghyung Cho. <yhcting77@gmail.com>
 * All rights reserved.
 *
 * This file is part of NetMBuddy
 *
 * This program is licensed under the FreeBSD license
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation
 * are those of the authors and should not be interpreted as representing
 * official policies, either expressed or implied, of the FreeBSD Project.
 *****************************************************************************/

package free.yhc.netmbuddy;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import free.yhc.baselib.Logger;
import free.yhc.abaselib.util.UxUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YTMPActivity extends Activity {
    private static final boolean DBG = Logger.DBG_DEFAULT;
    private static final Logger P = Logger.create(YTMPActivity.class, Logger.LOGLV_DEFAULT);

    private static final int PERM_REQ_ESSENTIAL = 0;

    private void
    handleSendText(Intent intent) {
        String recivedText = intent.getData().toString();

        // Filter the received text to match a valid search term:
        // youtube.+v=  ---> "youtube" + one or more chars + "v="
        // [^&|\n]+     ---> one or more chars + "&" or end of line
        Pattern p = Pattern.compile("youtube.+v=[^&|\n]+");
        Matcher m = p.matcher(recivedText);
        String path = "";
        if(m.find())
            path = m.group();

        Intent i = new Intent(this, YTVideoSearchKeywordActivity.class);
        i.putExtra(YTSearchActivity.KEY_TEXT, path);
        startActivity(i);
    }

    private void
    startMainActivity() {
        Intent i = new Intent(this, PlaylistActivity.class);
        startActivity(i);

        Intent intent = getIntent();
        String action = intent.getAction();

        if (Intent.ACTION_VIEW.equals(action))
            handleSendText(intent);
    }

    @Override
    public void
    onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (YTMPApp.hasEssentialPermissions()) {
            startMainActivity();
            finish();
        } else {
            ActivityCompat.requestPermissions(
                    this, YTMPApp.getEssentialPermissions(), PERM_REQ_ESSENTIAL);
        }
    }

    @Override
    public void
    onRequestPermissionsResult(final int requestCode,
                               @NonNull final String[] permissions,
                               @NonNull final int[] grantResults) {
        P.bug(PERM_REQ_ESSENTIAL == requestCode);
        if (grantResults.length > 0
                && PackageManager.PERMISSION_GRANTED == grantResults[0]) {
            YTMPApp.initApplicationPostEssentialPermissions();
            startMainActivity();
        } else {
            if (!YTMPApp.hasEssentialPermissions()) {
                UxUtil.showTextToast(R.string.err_essential_perm);
            }
        }
        finish();
    }

    @Override
    protected void
    onResume() {
        super.onResume();
    }

    @Override
    protected void
    onPause() {
        super.onPause();
    }

    @Override
    protected void
    onStop() {
        super.onStop();
    }

    @Override
    protected void
    onDestroy() {
        super.onDestroy();
    }

    @Override
    public void
    onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void
    onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Do nothing!
    }

    @Override
    public void
    onBackPressed() {
        super.onBackPressed();
    }
}
