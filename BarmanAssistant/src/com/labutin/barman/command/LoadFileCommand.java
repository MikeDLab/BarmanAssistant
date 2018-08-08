package com.labutin.barman.command;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.CocktailService;
import com.labutin.barman.specification.cocktail.CocktailSpecification;

public class LoadFileCommand implements Command {
	private static final int fileMaxSize = 100 * 1024;
	private static final int memMaxSize = 100 * 1024;
//	private String filePath = "/Users/Mike/Documents/EpamJava/XMLParserWeb/data/";
	private static final long serialVersionUID = 1L;
	private File file;
	private static String fileFullPath;
	private CocktailService receiver;

	public LoadFileCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("LOAD IMAGE");
		InputStream inputStream = null; // input stream of the upload file
		// obtains the upload file part in this multipart request
		Part filePart;
		try {
			filePart = request.getPart("photo");

			if (filePart != null) {
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				// obtains input stream of the upload file
				// the InputStream will point to a stream that contains
				// the contents of the file
				inputStream = filePart.getInputStream();
				receiver = new CocktailService();
				String ingrId = request.getParameter("cocktailId");
				System.out.println("ID cocktail: " + ingrId);

			}
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return PageEnum.COCKTAIL_LIST_PAGE;

	}

}
