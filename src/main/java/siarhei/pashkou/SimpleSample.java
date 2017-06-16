package siarhei.pashkou;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class SimpleSample {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {
		/*
		 * System.out.println("Welcome to OpenCV " + Core.VERSION); Mat m = new
		 * Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
		 * System.out.println("OpenCV Mat: " + m); Mat mr1 = m.row(1);
		 * mr1.setTo(new Scalar(1)); Mat mc5 = m.col(5); mc5.setTo(new
		 * Scalar(5)); System.out.println("OpenCV Mat data:\n" + m.dump());
		 */

		justTest();
	}

	public static void justTest() {
		/*
		 * Mat image2 = new Mat(480,640,CvType.CV_8UC3); Mat image3 = new
		 * Mat(new Size(640,480), CvType.CV_8UC3);
		 *
		 * System.out.println(image2 + "rows " + image2.rows() + " cols " +
		 * image2.cols() + " elementsize " + image2.elemSize());
		 */
		VideoCapture  v;
		Mat image = new Mat(new Size(3, 3), CvType.CV_8UC3, new Scalar(new double[] { 128, 3, 4 }));
		filter(image);
		System.out.println("OpenCV Mat data:\n" + image.dump());
	}

	public static void filter(Mat image) {
		int totalBytes = (int) (image.total() * image.elemSize());
		byte buffer[] = new byte[totalBytes];
		image.get(0, 0, buffer);
		for (int i = 0; i < totalBytes; i++) {
			if (i % 3 == 0)
				buffer[i] = 0;
		}
		image.put(0, 0, buffer);
	}

	public Mat openFile(String fileName) throws Exception{
		 Mat newImage = Imgcodecs.imread(fileName);
		 if(newImage.dataAddr()==0){
		 throw new Exception ("Couldn't open file "+fileName);
		 }
		 return newImage;
		}

}
