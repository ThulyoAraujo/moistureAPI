#include <WiFi.h>
#include <HTTPClient.h>

#define sensor 4

const char* ssid = "wifi";
const char* password = "senhaWifi";
const char* serverUrl = "http://192.168.0.189:8080/api/reader/create";

void setup() {
  Serial.begin(9600);
}

void loop() {
  int data = identifyData();
  startConnection();
  sendData(data);
  endConnection();
  delay(1000);
}

int identifyData() {
  int data = analogRead(sensor);
  Serial.println(data);
  return data;
}

void sendData(int data) {
    if (WiFi.status() == WL_CONNECTED) {
    HTTPClient http;

    http.begin(serverUrl);
    http.addHeader("Content-Type", "application/json");

    String payload = "{\"moisture\":\"" + String(data) + "\"}";
    Serial.println(payload);
    int httpResponseCode = http.POST(payload);

    if (httpResponseCode == 200) {
      Serial.println("Dados enviados com sucesso para a API.");
    } else {
      Serial.println("Erro ao enviar dados para a API.");
    }

    http.end();
  }
}

void startConnection() {
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Conectando ao Wi-Fi...");
  }
  Serial.println("Conectado ao Wi-Fi!");
}

void endConnection() {
  WiFi.mode(WIFI_OFF);
  Serial.println("Wi-Fi desativado.");
}