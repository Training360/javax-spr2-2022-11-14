package training.empappwebsocketclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class EmpappWebsocketClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmpappWebsocketClientApplication.class, args);


	}

	@Override
	public void run(String... args) {
		log.info("Hello World");
		log.info("{} - {} - {}", "Hello", "World", "Java");

		var client = new WebSocketStompClient(new SockJsClient(List.of(new WebSocketTransport(new StandardWebSocketClient()))));
		client.setMessageConverter(new MappingJackson2MessageConverter()); // JSON
		var sessionHandler = new MessageStompSessionHandler();
		client.connect("ws://localhost:8080/websocket-endpoint", sessionHandler);

		new Scanner(System.in).nextLine();
	}


}
