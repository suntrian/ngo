package org.ngo.util.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FtpUtil {
    private Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    private FTPClient client;
    private FtpProp vo;
    private FTPConstant ftpConstant = new FTPConstant();

    public FtpUtil(FtpProp vo) {
        this.vo = vo;
        client = createFTPClient(vo);
    }

    //创建变连接FTP
    private FTPClient createFTPClient(FtpProp vo) {
        FTPClient client = new FTPClient();
        int       reply  = -1;
        try {
            client.connect(vo.hostName, vo.port);
            client.login(vo.username, vo.password);
            reply = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                return null;
            } else {
                client.setControlEncoding(vo.remoteEncoding);
                client.setFileType(FTPClient.BINARY_FILE_TYPE);
                if (vo.passiveMode) {
                    client.enterLocalPassiveMode();
                } else {
                    client.enterRemotePassiveMode();
                }
                client.cwd(vo.remoteDir);
            }
            return client;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    //判断远程文件是否存在
    public boolean isExists(String fileName){
        List<String> list = listFile(vo.remoteDir);
        if (list.contains(fileName)) {
            return true;
        }
        return false;
    }

    //下载远程文件
    public boolean downLoad(String fileName){
        String localfileName = vo.localDir + File.separator + fileName;
        FileUtil.createFiles(localfileName);
        OutputStream out = null;
        try {
            out = new FileOutputStream(localfileName, true);
            client.retrieveFile(new String(fileName.getBytes(vo.remoteEncoding), "ISO-8859-1"), out);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return reply("DOWNLOAD", localfileName, fileName);
    }

    //下载远程目录
    public boolean downLoadDir(String directory){
        List<String> files = listFile(directory);
        for (String s : files) {
            downLoad(s);
        }
        return true;
    }

    //删除远程文件
    public boolean deleteFile(String fileName){
        if (isExists(fileName)) {
            try {
                client.deleteFile(new String(fileName.getBytes(vo.remoteEncoding), "ISO-8859-1"));
                return reply("DELETE", "", fileName);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return false;
    }

    //删除远程目录
    public boolean deleteDir(String directory){
        List<String> files = listFile(directory);
        try {
            for (String s : files) {
                deleteFile(s);
            }
            List<String> dirs = listDir(directory);
            for (int i = dirs.size() - 1; i >= 0; i--) {
                client.removeDirectory(new String(dirs.get(i).getBytes(vo.remoteEncoding), "ISO-8859-1"));
            }
            client.removeDirectory(new String(directory.getBytes(vo.remoteEncoding), "ISO-8859-1"));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return reply("DELETE", "", directory);
    }

    //上传本地文件到远程目录
    public boolean putFile(String fileName, String remoteFileName, boolean isDelete){
        File file = new File(fileName);
        return putFile(file, remoteFileName, isDelete);
    }

    //上传本地文件到远程目录
    public boolean putFile(File file, String remoteFileName, boolean isDelete){
        String fileName = remoteFileName;
        String path     = "";
        String parent   = getParentPath(remoteFileName);
        if (remoteFileName.lastIndexOf("/") != -1) {
            path = remoteFileName.substring(0, remoteFileName.lastIndexOf("/"));
            fileName = remoteFileName.substring(remoteFileName.lastIndexOf("/") + 1);
            mkDir(path);
            changeWorkDir(path);
        }
        try (InputStream in = new FileInputStream(file)) {
            if (isDelete) {
                deleteFile(new String(file.getName().getBytes(vo.remoteEncoding), "ISO-8859-1"));
            }
            client.appendFile(new String(fileName.getBytes(vo.remoteEncoding), "ISO-8859-1"), in);
            return reply("UPLOAD", file.getAbsoluteFile().toString(), remoteFileName);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    //上传本地目录到远程
    public boolean putDir(String fileName, String remoteDir){
        File file = new File(fileName);
        return putDir(file, remoteDir);
    }

    //上传本地目录到远程
    public boolean putDir(File file, String remoteDir){
        List<File> list = FileUtil.listFile(file);
        for (File f : list) {
            String name = f.getAbsolutePath();
            name = name.substring(name.indexOf(file.getName())).replaceAll("\\\\", "/");
            putFile(f, remoteDir + "/" + name, true);
        }
        return true;
    }

    //创建文件夹
    public boolean mkDir(String directory){
        directory = directory.replaceAll("//", "/");
        if (directory.startsWith("/")) {
            directory = directory.substring(1);
        }
        if (directory.endsWith("/")) {
            directory = directory.substring(0, directory.length() - 1);
        }
        try {
            String[] str = (new String(directory.getBytes(vo.remoteEncoding), "ISO-8859-1")).split("/");
            String t = "";
            String parnet = "";
            for (int i = 0; i < str.length; i++) {
                t += ("/" + str[i]);
                if (!isExists(t.substring(1))) {
                    client.makeDirectory(str[i]);
                }
                client.changeWorkingDirectory(str[i]);
                parnet += "../";
            }
            if (str.length >= 1) {
                client.changeWorkingDirectory(parnet);
            }

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }


    //获取远程文件列表
    public List<String> listFile(String directory){
        List<String> list = new ArrayList<String>();
        try {
            FTPFile[] files = client.listFiles(directory);
            for (int i = 0; i < files.length; i++) {
                String t = (directory + "/" + files[i].getName()).replaceAll("//", "/");
                if (files[i].isFile()) {
                    list.add(t);
                } else if (files[i].isDirectory()) {
                    list.addAll(listFile((t + "/").replaceAll("//", "/")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取远程文件夹的目录结构
    public LinkedList<String> listDir(String directory){
        LinkedList<String> list = new LinkedList<String>();
        try {
            FTPFile[] files = client.listFiles(directory);
            for (int i = 0; i < files.length; i++) {
                String t = (directory + "/" + files[i].getName()).replaceAll("//", "/");
                if (files[i].isDirectory()) {
                    list.add(t);
                    list.addAll(listDir(t + "/"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取远程文件属性以Map形式返回
    public Map<String,FileAttr> listFileAttr(String directory){
        Map<String, FileAttr> map = new HashMap<String, FileAttr>();
        try {
            FTPFile[] files = client.listFiles(directory);
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    FTPFile file = files[i];
                    String fileName = directory + file.getName();
                    FileAttr attr = new FileAttr();
                    attr.fileName = fileName;
                    attr.ModifyTime = file.getTimestamp().getTime();
                    attr.size = file.getSize();
                    map.put(fileName, attr);
                } else if (files[i].isDirectory()) {
                    map.putAll(listFileAttr(directory + files[i].getName() + "/"));
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return map;
    }

    //改变FTP连接的工作目录
    public boolean changeWorkDir(String directory){
        try {
            client.cwd(directory);
            return true;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    //获取当前连接的工作目录
    public String getWorkDir(){
        try {
            return client.printWorkingDirectory();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }

    //重命名文件
    public boolean changName(String oldName, String newName){
        return false;
    }

    //返回FTPCliend对象(已经打开连接)
    public FTPClient client(){
        return null;
    }

    //释放所有的资源
    public void destory(){
        if (CheckUtil.valid(client)) {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //通过FTP响应码判断是否操作成功
    private boolean reply(String operation) {
        return reply(operation, "", "");
    }

    private boolean reply(String operation, String localFile, String remoteFile) {
        int    replyCode = client.getReplyCode();
        FtpLog log = new FtpLog();
        log.host = vo.hostName;
        log.operation = operation;
        log.localFile = localFile;
        log.remoteFile = remoteFile;
        log.ReplyCode = replyCode;
        log.ReplyCodeDesc = ftpConstant.REPLYCODE.get(replyCode);
        logger.info(log.toString());
        return FTPReply.isPositiveCompletion(replyCode);
    }

    private String getParentPath(String file) {
        if (file.indexOf("/") != -1) {
            String temp = null;
            Pattern p = Pattern.compile("[/]+");
            Matcher m = p.matcher(file);
            int i = 0;
            while (m.find()) {
                temp = m.group(0);
                i += temp.length();
            }
            String parent = "";
            for (int j = 0; j < i; j++) {
                parent += "../";
            }
            return parent;
        } else {
            return "./";
        }
    }

    class FtpProp {
        protected String  hostName;
        protected int     port;
        protected String  username;
        protected String  password;
        protected String  remoteDir;
        protected String  localDir;
        protected String  remoteEncoding;
        protected boolean passiveMode;
    }

    class FtpLog {
        public String host;
        public String operation;
        public int    ReplyCode;
        public String localFile;
        public String remoteFile;
        public String ReplyCodeDesc;
        public String createTime = DateUtil.currentDateTime();
    }

    class FTPConstant {
        /**
         * FTP状态码及其描述
         */
        public final  Map<Integer,String> REPLYCODE        = new HashMap<Integer,String>(){
            {
                put(120, "服务已就绪，在 nnn 分钟后开始。");
                put(125, "数据连接已打开，正在开始传输。");
                put(150, "文件状态正常，准备打开数据连接。");
                put(202, "未执行命令，站点上的命令过多。");
                put(211, "系统状态，或系统帮助答复。");
                put(212, "目录状态。");
                put(213, "文件状态。");
                put(214, "帮助消息。");
                put(215, "NAME 系统类型，其中，NAME 是 Assigned Numbers 文档中所列的正式系统名称。");
                put(220, "服务就绪，可以执行新用户的请求。");
                put(221, "服务关闭控制连接。如果适当，请注销。");
                put(225, "数据连接打开，没有进行中的传输。");
                put(226, "关闭数据连接。请求的文件操作已成功（例如，传输文件或放弃文件）。");
                put(227, "进入被动模式 (h1,h2,h3,h4,p1,p2)。");
                put(230, "用户已登录，继续进行。");
                put(250, "请求的文件操作正确，已完成。");
                put(257, "已创建“PATHNAME”。");
                put(332, "需要登录帐户。");
                put(350, "请求的文件操作正在等待进一步的信息。");
                put(425, "无法打开数据连接。");
                put(426, "Connection closed; transfer aborted.");
                put(450, "未执行请求的文件操作。文件不可用（例如，文件繁忙）。");
                put(451, "请求的操作异常终止：正在处理本地错误。");
                put(452, "未执行请求的操作。系统存储空间不够。");
                put(501, "在参数中有语法错误。");
                put(502, "未执行命令。");
                put(503, "错误的命令序列。");
                put(504, "未执行该参数的命令。");
                put(530, "未登录。");
                put(532, "存储文件需要帐户。");
                put(550, "未执行请求的操作。文件不可用（例如，未找到文件，没有访问权限）。");
                put(551, "请求的操作异常终止：未知的页面类型。");
                put(552, "请求的文件操作异常终止：超出存储分配（对于当前目录或数据集）。");
                put(553, "未执行请求的操作。不允许的文件名。");
            }
        };
        //用于编码转换
        public String              ISO_ECODING      = "ISO-8859-1";
        //程序运行的编码
        public String              PROJECT_ENCODING = "UTF-8";
    }

    class FileAttr {
        public String fileName;
        public Date ModifyTime;
        public Long size;
    }
}
