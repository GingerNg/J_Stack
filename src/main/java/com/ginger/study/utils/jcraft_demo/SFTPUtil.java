package com.ginger.study.utils.jcraft_demo;


import com.jcraft.jsch.*;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

public class SFTPUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SFTPUtil.class);

    private String host;// = PropertyUtil.getProperty("host");

    private String username;// = PropertyUtil.getProperty("username");

    private String password;// = PropertyUtil.getProperty("password");

    private int port;// = Integer.valueOf(PropertyUtil.getProperty("port"));

    private ChannelSftp sftp = null;

    private static String LOCAL_PATH;// = PropertyUtil.getProperty("file_local_path"); // Web服务器地址

    private static String REMOTE_PATH;// = PropertyUtil.getProperty("file_server_path"); // 文件服务器地址

    private static String TOOL_PATH = "tools/";
    private static String DATA_PATH = "data/";
    private static String USER_DATA_PATH = "userdata/";

    private final String seperator = "/";

    /**
     * connect server via sftp
     */
    public void connect() {
        try {
            if (sftp != null) {
                logger.warn("sftp is not null");
            }
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            Session sshSession = jsch.getSession(username, host, port);
            logger.info("Session created.");
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            logger.info("Session connected.");
            logger.info("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            logger.info("Connected to " + host + ".");
        } catch (Exception e) {
            logger.error("sftp connect exception", e);
            throw new RuntimeException("sftp connect exception", e);
        }
    }

    public static void main(String[] args){
        /**
         * sftp 功能测试
         */
//        SFTPUtil sftpUtil = new SFTPUtil();
//        sftpUtil.setHost("X.X.X.X");
//        sftpUtil.setPassword("XX");
//        sftpUtil.setUsername("XX");
//        sftpUtil.setPort(22);
//
//        sftpUtil.connect();
//        System.out.println(sftpUtil.isFileExist("/home/XX/source"));
//        sftpUtil.disconnect();
//        System.exit(0);

        /**
         * session alive 测试
         * 测试当网络断开后,session是否能检测到--> 网络断开后isConnected仍然为true
         */
            try{
                JSch jsch = new JSch();
                Session sshSession = jsch.getSession("XX", "X.X.X.X", 22);
                logger.info("Session created.");
                sshSession.setPassword("XX");
                Properties sshConfig = new Properties();
                sshConfig.put("StrictHostKeyChecking", "no");
                sshSession.setConfig(sshConfig);
                sshSession.connect();
                logger.info("Session connected.");
                while(true){
                    Thread.sleep(1000);
                    System.out.println(sshSession.isConnected());
                }
            }catch (Exception e){
                logger.info("session alive 测试");
            }


//        try{
//            Thread.sleep(1000000);
//        }catch (Exception e){
//            logger.info("end");
//        }


    }

    public boolean isFileExist(String filePath){
        boolean existed = false;
        try{
            Vector files = sftp.ls(filePath); // http://blog.csdn.net/qq924862077/article/details/48039567   Java集合之Vector
            Iterator iterator = files.iterator();
            while(iterator.hasNext())
            {
                System.out.println(iterator.next());   //所有文件的集合
            }


            if (sftp.ls(filePath)==null){
                existed = true;
            }
        }catch (Exception e){
            logger.error("{}",e);
        }

        return existed;
    }

    /**
     * Disconnect with server
     */
    public void disconnect() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                this.sftp.disconnect();
            } else if (this.sftp.isClosed()) {
                logger.info("sftp is closed already");
            }
        }
    }

    private void download(String directory, String downloadFile, String saveFile) {
        try {
            sftp.cd(directory);
            File file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filepath 文件服务器目录
     */
    public static File downloadFile(String filepath) {
        try {
            File remoteFile = new File(filepath);
            String remotePath = remoteFile.getParent();
            String[] arr = filepath.split("\\/");
            String fileName = arr[arr.length - 1];

            String tmpFilePath = LOCAL_PATH + fileName;
            File tmpFile = new File(tmpFilePath);

            if (tmpFile.isFile() && tmpFile.exists())
                return tmpFile;

            SFTPUtil ftp = new SFTPUtil();
            ftp.connect();
            ftp.download(remotePath, fileName, tmpFile);
            ftp.disconnect();

            return new File(tmpFilePath);

        } catch (Exception e) {
            logger.error("filepath not found exception", e);
            throw new RuntimeException("filepath not found exception", e);
        }
    }

    /**
     * @param directory    文件服务器地址
     * @param downloadFile 文件名
     * @param file         Web服务器路径
     */
    public void download(String directory, String downloadFile, File file) {
        try {
            sftp.cd(directory);
            sftp.get(downloadFile, new FileOutputStream(file));
        } catch (Exception e) {
            logger.error("file download exception", e);
            throw new RuntimeException("file download exception", e);
        }
    }

    /**
     * upload all the files to the server
     */
//    public void upload() {
//        List<String> fileList = this.getFileEntryList(fileListPath);
//        try {
//            if (fileList != null) {
//                for (String filepath : fileList) {
//                    String localFile = this.LOCAL_PATH + this.seperator + filepath;
//                    File file = new File(localFile);
//
//                    if (file.isFile()) {
//                        System.out.println("localFile : " + file.getAbsolutePath());
//                        String remoteFile = this.REMOTE_PATH + this.seperator + filepath;
//                        System.out.println("REMOTE_PATH:" + remoteFile);
//                        File rfile = new File(remoteFile);
//                        String rpath = rfile.getParent();
//                        try {
//                            createDir(rpath, sftp);
//                        } catch (Exception e) {
//                            System.out.println("*******create path failed" + rpath);
//                        }
//
//                        this.sftp.put(new FileInputStream(file), file.getName());
//                        System.out.println("=========upload down for " + localFile);
//                    }
//                }
//            }
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (SftpException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    /**
     * upload all the FileInputStream to the server
     */
    public void uploadInputStream(InputStream fileInputStream, String[] fileList) {
        try {
            if (fileList != null) {
                for (String filepath : fileList) {
                    String remoteFile = this.REMOTE_PATH + this.seperator + filepath;
                    logger.info("REMOTE_PATH", remoteFile);
                    File rfile = new File(remoteFile);
                    String rpath = rfile.getParent();
                    try {
                        createDir(rpath, sftp);
                    } catch (Exception e) {
                        logger.error("create path failed" + rpath, e);
                        throw new RuntimeException("create path failed" + rpath, e);
                    }

                    String[] files = filepath.split("\\/");
                    this.sftp.put(fileInputStream, files[files.length - 1]);
                    logger.info("upload file success ", files[files.length - 1]);
                }
            }
        } catch (SftpException e) {
            logger.error("upload file exception", e);
            throw new RuntimeException("upload file exception", e);
        }
    }

    /**
     * create Directory
     *
     * @param filepath
     * @param sftp
     */
    private void createDir(String filepath, ChannelSftp sftp) {
        boolean bcreated = false;
        boolean bparent = false;
        File file = new File(filepath);
        String ppath = file.getParent();
        try {
            this.sftp.cd(ppath);
            bparent = true;
        } catch (SftpException e1) {
            bparent = false;
        }
        try {
            if (bparent) {
                try {
                    this.sftp.cd(filepath);
                    bcreated = true;
                } catch (Exception e) {
                    bcreated = false;
                }
                if (!bcreated) {
                    this.sftp.mkdir(filepath);
                    bcreated = true;
                }
                return;
            } else {
                createDir(ppath, sftp);
                this.sftp.cd(ppath);
                this.sftp.mkdir(filepath);
            }
        } catch (SftpException e) {
            logger.error("mkdir failed :" + filepath, e);
            throw new RuntimeException("mkdir failed :" + filepath, e);
        }

        try {
            this.sftp.cd(filepath);
        } catch (SftpException e) {
            logger.error("can not cd into :" + filepath, e);
            throw new RuntimeException("can not cd into :" + filepath, e);
        }

    }

    /**
     * get all the files need to be upload or download
     *
     * @param file
     * @return
     */
    private List<String> getFileEntryList(String file) {
        ArrayList<String> fileList = new ArrayList<String>();
        InputStream in = null;
        try {

            in = new FileInputStream(file);
            InputStreamReader inreader = new InputStreamReader(in);

            LineNumberReader linreader = new LineNumberReader(inreader);
            String filepath = linreader.readLine();
            while (filepath != null) {
                fileList.add(filepath);
                filepath = linreader.readLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in != null) {
                in = null;
            }
        }

        return fileList;
    }

//    public static String[] toolUpload(FileUploadRequest fileUploadRequest) {
//
//        return fileupload(fileUploadRequest, TOOL_PATH);
//    }
//
//    public static String[] dataUpload(FileUploadRequest fileUploadRequest) {
//        return fileupload(fileUploadRequest, DATA_PATH);
//    }
//
//    public static String[] userDataUpload(FileUploadRequest fileUploadRequest) {
//        return fileupload(fileUploadRequest, USER_DATA_PATH);
//    }
//
//    private static String[] createFilePath(FileUploadRequest fileUploadRequest, String addPath) {
//
//        String fileName = fileUploadRequest.getContentDispositionHeader().getFileName();
//        if (null == fileName || "".equals(fileName)) {
//            logger.error("上传文件名为空！");
//            throw new RuntimeException("上传文件名为空！");
//        }
//
//        String unFileName = UUID.randomUUID().toString() + "." + fileName;
//
//        unFileName = addPath + DateUtil.formatDateByDate(new Date()) + "/" + unFileName;
//
//        String[] fileList = {unFileName};
//
//        return fileList;
//    }

//    /**
//     * 文件上传公用方法
//     *
//     * @param fileUploadRequest
//     * @throws IOException
//     */
//    public static String[] fileupload(FileUploadRequest fileUploadRequest, String pathName) {
//
//        String[] fileList;
//        try {
//
//            validateFileSize(fileUploadRequest);
//
//            fileList = createFilePath(fileUploadRequest, pathName);
//
//            SFTPUtil ftp = new SFTPUtil();
//            ftp.connect();
//            ftp.uploadInputStream(fileUploadRequest.getFileInputStream(), fileList);
//            ftp.disconnect();
//
//        } catch (IOException e) {
//            logger.error("fileupload exception: {}", e);
//            throw new RuntimeException("fileupload exception: {}", e);
//        }
//
//        return fileList;
//    }
//
//    private static void validateFileSize(FileUploadRequest fileUploadRequest) throws IOException {
//        int read;
//        byte[] bytes = new byte[1024];
//
//        InputStream inputStream = fileUploadRequest.getFileInputStream();
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//        while ((read= inputStream.read(bytes)) != -1) {
//            baos.write(bytes, 0, read);
//            // 后端控制上传文件大小 < 1024*1024*128  128M
//            if (baos.size() > 128*1024*1024) {
//                logger.error("文件大小超过限制！");
//                throw new RuntimeException("文件大小超过限制！");
//            }
//        }
//        fileUploadRequest.setFileInputStream(new ByteArrayInputStream(baos.toByteArray()));
//    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the sftp
     */
    public ChannelSftp getSftp() {
        return sftp;
    }

    /**
     * @param sftp the sftp to set
     */
    public void setSftp(ChannelSftp sftp) {
        this.sftp = sftp;
    }

    /**
     * @return the LOCAL_PATH
     */
    public String getLOCAL_PATH() {
        return LOCAL_PATH;
    }

    /**
     * @param LOCAL_PATH the LOCAL_PATH to set
     */
    public void setLOCAL_PATH(String LOCAL_PATH) {
        this.LOCAL_PATH = LOCAL_PATH;
    }

    /**
     * @return the REMOTE_PATH
     */
    public String getREMOTE_PATH() {
        return REMOTE_PATH;
    }

    /**
     * @param REMOTE_PATH the REMOTE_PATH to set
     */
    public void setREMOTE_PATH(String REMOTE_PATH) {
        this.REMOTE_PATH = REMOTE_PATH;
    }


}

