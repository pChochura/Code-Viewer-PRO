import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class JavaFXImageViewer extends Application {
	private final List<String> list = new ArrayList<>();
	int currentImage = 0;
	double clickSceneX, releaseSceneX;
	Button leftButton, rightButton;
	ImageView imageView;
	Label label;
	@Override
	public void start(Stage primaryStage) {
		try {
			list.add("1.jpg");
			list.add("2.jpg");
			list.add("3.jpg");
			list.add("4.jpg");
			list.add("5.jpg");
			BorderPane root = new BorderPane();
			label = new Label(currentImage + 1 + "/" + list.size());
			Image images[] = new Image[list.size()];
			for (int i = 0; i < list.size(); i++) {
				images[i] = new
						Image(getClass().getResourceAsStream(list.get(i)));
			}
			imageView = new ImageView(images[currentImage]);
			imageView.setCursor(Cursor.CLOSED_HAND);
			leftButton = new Button("<");
			rightButton = new Button(">");
			leftButton.setOnAction(e -> {
				currentImage = currentImage - 1;
				if (currentImage < 0) {
					currentImage = list.size() - 1;
				}
				imageView.setImage(images[currentImage]);
				label.setText(currentImage + 1 + "/" + list.size());
			});
			rightButton.setOnAction(e -> {
				currentImage = currentImage + 1;
				if (currentImage >= list.size()) {
					currentImage = 0;
				}
				imageView.setImage(images[currentImage]);
				label.setText(currentImage + 1 + "/" + list.size());
			});
			imageView.setOnMousePressed(e -> {
				clickSceneX = e.getSceneX();
			});
			imageView.setOnMouseReleased(e -> {
				releaseSceneX = e.getSceneX();
				if (clickSceneX > releaseSceneX) {
					leftButton.fire();
				} else {
					rightButton.fire();
				}
			});
			imageView.setFitWidth(500);
			HBox hBox = new HBox();
			hBox.setSpacing(15);
			hBox.setAlignment(Pos.CENTER);
			hBox.getChildren().addAll(leftButton, imageView, rightButton);
			root.setCenter(hBox);
			BorderPane.setAlignment(label, Pos.CENTER);
			root.setTop(label);
			Scene scene = new Scene(root, 800, 800);
			primaryStage.setTitle("Image Viewer");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}