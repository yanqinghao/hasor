/*
 * Copyright 2008-2009 the original 赵永春(zyc@hasor.net).
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
package net.test.aliyun.oss;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import net.hasor.core.ApiBinder;
import net.hasor.core.AppContext;
import net.hasor.core.AppContextAware;
import net.hasor.core.EventListener;
import net.hasor.core.Module;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
/**
 * 
 * @version : 2014年8月1日
 * @author 赵永春(zyc@hasor.net)
 */
public class ImportDir implements Module {
    public static String BasePath = "C:/Users/yongchun.zyc/Desktop/apis";
    //
    public void loadModule(ApiBinder apiBinder) throws Throwable { 
    	
    }
    // 
    public void echPath(AppContext app, File path) throws FileNotFoundException {
        if (path.isFile()) {
            app.fireAsyncEvent("UPLOAD", path);
        } else {
            File[] fs = path.listFiles();
            for (File file : fs) {
                echPath(app, file);
            }
        }
    }
}
class Upload implements EventListener, AppContextAware {
    private OSSClient      client = null;
    private ServletContext sc     = null;
    public void setAppContext(AppContext appContext) {
        this.client = appContext.getInstance(OSSClient.class);
        this.sc = appContext.getInstance(ServletContext.class);
    }
    public void onEvent(String event, Object[] params) throws Throwable {
        File file = (File) params[0];
        try {
            // 获取指定文件的输入流
            InputStream content = new FileInputStream(file);
            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();
            // 必须设置ContentLength
            meta.setContentLength(file.length());
            int point = file.getName().lastIndexOf('.');
            if (point != -1) {
                String mtype = sc.getMimeType("." + file.getName().substring(point));
                meta.setContentType(mtype);
            }
            // 上传Object.
            String key = file.getAbsolutePath();
            key = key.replace("\\", "/");
            key = key.substring(ImportDir.BasePath.length());
            if (key.charAt(0) == '/')
                key = key.substring(1);
            PutObjectResult result = client.putObject("www-hasor", key, content, meta);
            System.out.println("OK:" + result.getETag() + "->");
        } catch (Exception e) {
            System.err.println("\nError ->" + file.toString() + "\n");
        }
    }
}