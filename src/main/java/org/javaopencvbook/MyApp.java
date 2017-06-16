package org.javaopencvbook;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.javaopencvbook.utils.MyImageProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class MyApp {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	private JFrame frame;
	private JLabel imageLabel;

	public static void main(String[] args) {
		MyApp app = new MyApp();
		app.initGUI();
		app.runMainLoop(args);
	}

	private void initGUI() {
		frame = new JFrame("Camera Input Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		imageLabel = new JLabel();
		frame.add(imageLabel);
		frame.setVisible(true);
	}



	private void runMainLoop(String[] args) {
		MyImageProcessor imageProcessor = new MyImageProcessor();
		Mat webcamMatImage = new Mat();
		Image tempImage;
		VideoCapture capture = new VideoCapture("C:\\London.avi");
		capture.set(Videoio.CV_CAP_PROP_FRAME_WIDTH, 640);
		capture.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT, 480);

		if (capture.isOpened()) {
			while (true) {
				capture.read(webcamMatImage);
				if (!webcamMatImage.empty()) {
					tempImage = imageProcessor.toBufferedImage(webcamMatImage);
					ImageIcon imageIcon = new ImageIcon(tempImage, "Captured video");
					imageLabel.setIcon(imageIcon);
					frame.pack(); // this will resize the window to fit the
									// image
				} else {
					System.out.println(" -- Frame not captured -- Break!");
					break;
				}
			}
		} else {
			System.out.println("Couldn't open capture.");
		}

	}

}
