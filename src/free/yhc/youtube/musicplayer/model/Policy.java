/*****************************************************************************
 *    Copyright (C) 2012 Younghyung Cho. <yhcting77@gmail.com>
 *
 *    This file is part of YTMPlayer.
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as
 *    published by the Free Software Foundation either version 3 of the
 *    License, or (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License
 *    (<http://www.gnu.org/licenses/lgpl.html>) for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************************/

package free.yhc.youtube.musicplayer.model;

public class Policy {
    public static final String  REPORT_RECEIVER         = "yhcting77@gmail.com";
    public static final String  APPDATA_DIR             = "/sdcard/ytmplayer/";
    public static final String  APPDATA_TMPDIR          = APPDATA_DIR + "tmp/";
    public static final String  APPDATA_LOGDIR          = APPDATA_DIR + "logs/";
    public static final String  APPDATA_CACHEDIR        = APPDATA_DIR + "cache/";
    // Downloaded video directory
    public static final String  APPDATA_VIDDIR          = APPDATA_DIR + "videos/";
    public static final String  EXTERNAL_DBFILE         = APPDATA_DIR + "ytmplayer.db";

    // --------------------------------------------------------------------
    // Youtube
    // --------------------------------------------------------------------
    public static final int     DEFAULT_VIDEO_VOLUME    = 50;
    public static final int     YTSEARCH_MAX_RESULTS    = 25; // 1 ~ 50
    public static final int     YTSEARCH_NR_PAGE_INDEX  = 10;
    public static final int     DEFAULT_VIDEO_QUAILITY  = YTHacker.YTQUALITY_SCORE_MIDLOW;

    // --------------------------------------------------------------------
    // Youtube Player
    // --------------------------------------------------------------------
    public static final int     YTPLAYER_RETRY_ON_ERROR = 3;
    // caching-ahead next video will started if current buffering reaches this value.
    public static final int     YTPLAYER_CACHING_TRIGGER_POINT = 80; // percent.

    // --------------------------------------------------------------------
    // Network access
    // --------------------------------------------------------------------
    public static final int     PROXY_PORT              = 59923;
    public static final String  HTTP_UASTRING
        = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.19 (KHTML, like Gecko) Ubuntu/12.04 Chromium/18.0.1025.168 Chrome/18.0.1025.168 Safari/535.19";
    // Too long : user waits too long time to get feedback.
    // Too short : fails on bad network condition.
    public static final int     NETWORK_CONN_TIMEOUT    = 5000;
    public static final int     NETOWRK_CONN_RETRY      = 3;
}
