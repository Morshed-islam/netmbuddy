/******************************************************************************
 * Copyright (C) 2015, 2016
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

package free.yhc.netmbuddy.ytapiv3;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import free.yhc.baselib.Logger;
import free.yhc.baselib.exception.BadResponseException;

public class YTRespSearch extends YTResp {
    private static final boolean DBG = Logger.DBG_DEFAULT;
    private static final Logger P = Logger.create(YTRespSearch.class, Logger.LOGLV_DEFAULT);

    static String
    getVideoSearchRequestUrl(String channelId,
                             String query,
                             String pageToken,
                             int maxResults) {
        P.bug(null != pageToken);
        assert null != pageToken;
        if (!pageToken.isEmpty())
            pageToken = "&pageToken=" + Uri.encode(pageToken, null);
        if (!channelId.isEmpty())
            channelId = "&channelId=" + channelId;
        if (!query.isEmpty())
            query = "&q=" + Uri.encode(query);
        return "https://www.googleapis.com/youtube/v3/search"
                + "?key=" + Uri.encode(YTApiFacade.API_KEY)
                + "&maxResults=" + maxResults
                + "&type=video"
                + "&part=id"
                + channelId
                + query
                + "&fields=nextPageToken,prevPageToken,pageInfo(totalResults,resultsPerPage)"
                    + ",items/id/videoId"
                + pageToken;
    }

    YTRespSearch() { }

    static YTResp.SearchListResponse
    parse(byte[] data) throws BadResponseException {
        JSONObject jo;
        try {
            jo = new JSONObject(new String(data));
        } catch (JSONException e) {
            if (DBG) P.w("Youtube data api gives bad response!!");
            throw new BadResponseException(e);
        }
        YTResp.SearchListResponse resp = new YTResp.SearchListResponse();
        resp.set(jo);
        return resp;
    }
}
