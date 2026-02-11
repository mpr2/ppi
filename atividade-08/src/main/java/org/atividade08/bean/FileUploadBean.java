package org.atividade08.bean;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.FileUploadSizeException;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletDiskFileUpload;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

public class FileUploadBean {
    private final JakartaServletDiskFileUpload sfu = new JakartaServletDiskFileUpload();
    private String directory;
    private String filename;
    private String allowedExtensions;
    
    private static final int MB = 1024 * 1024;
    private int size;

    private String errorMessage;

    public boolean doFilePost(HttpServletRequest request, ServletContext context) {
        if (request.getContentType() == null) {
            return false;
        }
        if (!request.getContentType().startsWith("multipart/form-data")) {
            setErrorMessage("Seu formulário não envia arquivos.");
            return false;
        }
        String path = context.getRealPath(getDirectory());
        try {
            sfu.setSizeMax(getSize()*MB);
            List<DiskFileItem> list = sfu.parseRequest(request);
            boolean uploaded = false;
            for (DiskFileItem item : list) {
                if (!item.isFormField()) {
                    filename = item.getName();
                    if ((filename != null) && (!filename.equals(""))) {
                        System.out.println(filename);
                        filename = (new File(filename)).getPath();
                        System.out.println(new File(path+"/"+filename));
                        if (isAllowed(filename)) {
                            item.write(new File(path+"/"+filename).toPath());
                            uploaded = true;
                        }
                        else {
                            setErrorMessage("Arquivo não permitido.");
                            return false;
                        }
                    }
                }
            }
            if (!uploaded) {
                setErrorMessage("Nenhum arquivo enviado.");
                return false;
            }
        }
        catch (FileUploadSizeException e) {
            setErrorMessage("Tamanho excedido");
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            setErrorMessage("Uma exceção ocorreu: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean isAllowed(String filename) {
        String lowerCaseName = filename.toLowerCase();
        StringTokenizer st = new StringTokenizer(this.allowedExtensions, " ,");
        while (st.hasMoreTokens()) {
            if (lowerCaseName.endsWith("." + st.nextToken())) {
                return true;
            }
        }
        return false;
    }

    public String getDirectory() {
        return directory;
    }

    public String getFilename() {
        return filename;
    }

    public int getSize() {
        return size;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setAllowedExtensions(String allowedExtensions) {
        this.allowedExtensions = allowedExtensions;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
