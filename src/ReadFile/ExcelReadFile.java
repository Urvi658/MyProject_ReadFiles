package ReadFile;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class ExcelReadFile extends SubString {

	private static final File Writename = null;

	public static void main(String[] args) throws IOException {

		String sPath = "D:\\";

		//ReadFile(sPath);

	}

	public static ArrayList<String> ReadFile(String sFolderPath) throws IOException {

		File file = new File(sFolderPath);
		File[] AllFiles = file.listFiles();
		String[] SubFolder = file.list();
		ArrayList<String> Writename = new ArrayList<String>();
		int iFolder = 0;
		int iFile = 0;
		if (AllFiles != null) {

			for (int i = 0; i < SubFolder.length; i++) {

				if (AllFiles[i].isDirectory()) {

					// System.out.println("It is a folder" + AllFiles[i]);
//					Writename.add(AllFiles[i].toString());
					String sColorFolder = "<span style = color:red>" + AllFiles[i].toString() + "</span>";
					Writename.add(sColorFolder);
					iFolder = iFolder + (i + 1);
					// System.out.println(iFolder);
					// System.out.println(AllFiles[i]);
					File Innerfile = new File(AllFiles[i].toString());
					File[] InnerAllFiles = Innerfile.listFiles();
					String[] InnerSubFolder = Innerfile.list();

					if (InnerAllFiles != null) {

						for (int j = 0; j < InnerSubFolder.length; j++) {

							if (InnerAllFiles[j].isDirectory()) {

//								Writename.add(InnerAllFiles[j].toString());
								// Writename.add("<style=color:red;>"+InnerAllFiles[j].toString()+"</style>");
//								Writename.add("<style>\nbody { color: green; }\n</style>\n"+ InnerAllFiles[j].toString());
								String sColorFolders = "<span style = color:red>" + InnerAllFiles[j].toString()
										+ "</span>";
								Writename.add(sColorFolders);
								iFolder = iFolder + (j + 1);

								// System.out.println("It is a Directory" + InnerAllFiles[j]);
							} else {

//								Writename.add(InnerAllFiles[j].toString());
								String sColorFile = "<span style = color:green>" + InnerAllFiles[j].toString()
										+ "</span>";
								Writename.add(sColorFile);

								// System.out.println("It is a file" + InnerAllFiles[j]);
								iFile = iFile + (j + 1);
								// System.out.println(iFile);
							}

						}
					}

				} else {

//				Writename.add(InnerAllFiles[j].toString());
					String sColorFiles = "<span style = color:green>" + AllFiles[i].toString() + "</span>";
					Writename.add(sColorFiles);

					// System.out.println("It is a file" + AllFiles[i]);
					iFile = iFile + (i + 1);
				}

			}

			HtmlWriterWithPieChart(Writename, iFolder, iFile);

		}

		return Writename;

	}

	public static void HtmlWriterWithPieChart(ArrayList<String> sRead, int iFolder, int iFile) throws IOException {
		File file = new File("D:\\Urvi Folder\\HtmlWriter.html");
		FileWriter fw = new FileWriter(file);
		System.out.println("TotalFolder" + iFolder);
		System.out.println("TotalFile" + iFile);
		System.out.println("TotalFileandFolder" + (iFolder + iFile));

//		int totalFolders = iFolder;
//		int totalFiles = iFile;
//
//		// Create the pie chart data
//		double[] values = { totalFolders, totalFiles };
//		String[] labels = { "Folders", "Files" };
//
//		// Generate the pie chart
//		String chartFilePath = DrawPiechart("D:\\Urvi Folder\\chart.png", "Folders vs Files", values, labels);
//		System.out.println(chartFilePath);
		// sRead.add("<style>\nbody { color: red; }\n</style>\n");

		if (!file.exists()) {

			file.createNewFile();

		}

		fw.write("<html>\n");
		fw.write("<head>\n");
		fw.write("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n");
		fw.write("<script type=\"text/javascript\">\n");
		fw.write("google.charts.load('current', {'packages':['corechart']});\n");
		fw.write("google.charts.setOnLoadCallback(drawChart);\n");
		fw.write("function drawChart() {\n");
		fw.write("var data = google.visualization.arrayToDataTable([\n");
		fw.write("['Type', 'Count'],\n");
		fw.write("['Folders', " + iFolder + "],\n");
		fw.write("['Files', " + iFile + "]\n");
		fw.write("]);\n");
		fw.write("var options = {\n");
		fw.write("title: 'Folders and Files'\n");
		fw.write(("}\n"));
		fw.write("var chart = new google.visualization.PieChart(document.getElementById('piechart'));\n");
		fw.write("chart.draw(data, options);\n");
		fw.write("}\n");
		fw.write("</script>\n");
		fw.write("</head>\n");
		fw.write("<body>\n");
		//fw.write("<div id=\"piechart\" style=\"width: 900px; height: 500px;\"></div>\n ");
		fw.write("  <div id=\"piechart\" style=\"position: absolute; right: 0; width: 500px; height: 400px;\"></div>\n");
		//fw.write("</div>\n");
		
		
		
		
		for (int i = 0; i < sRead.size(); i++) {
			fw.write(sRead.get(i) + "<br>\n");
		}

		for (int i = 0; i < sRead.size(); i++) {
//			System.out.println(sRead.get(i));
			fw.write(sRead.get(i) + "<br>");
			fw.write("</html>\n");
		}

		fw.close();
		String htmlFilePath = "D:\\Urvi Folder\\HtmlWriter.html";
		openHtmlFile(htmlFilePath);
	}

	public static void openHtmlFile(String filePath) {
		try {
			File htmlFile = new File(filePath);
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void WriteFilesandFolder(ArrayList<String> sRead) throws IOException {
		File file = new File("D:\\Urvi Folder\\FileandFolder.txt");
		FileWriter fw = new FileWriter(file);

		if (!file.exists()) {

			file.createNewFile();

		}

		for (int i = 0; i < sRead.size(); i++) {
			// System.out.println(sRead.get(i));

			// fw.write(sRead.get(i)+ "\n");

		}

		fw.close();

	}

}

//File newfile = new File(sFolderPath+"\\"+"me");
//boolean isCreated = newfile.createNewFile();
//System.out.println(isCreated);