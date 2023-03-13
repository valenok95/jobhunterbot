package ru.wallentos.jobhunterbot.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.wallentos.jobhunterbot.config.BotConfig;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class TelegramBotService extends TelegramLongPollingBot {
  @Autowired private BotConfig config;

  @Override
  public String getBotUsername() {
    return config.getName();
  }

  @Override
  public String getBotToken() {
    return config.getKey();
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      String receivedText = update.getMessage().getText();
      long chatId = update.getMessage().getChatId();
      log.info("message received: " + receivedText);

      switch (receivedText) {
        case "/start":
          try {
            startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
          } catch (TelegramApiException e) {
            throw new RuntimeException(e);
          }
          break;
        default:
          try {
            sendMessage(chatId, "Sorry, command not recognized=(");
          } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
          }
      }
    }
  }

  private void startCommandReceived(long chatId, String name) throws TelegramApiException {

    String message = "Hello " + name;
    sendMessage(chatId, message);
  }

  private void sendMessage(long chatId, String text) throws TelegramApiException {
    SendMessage message = new SendMessage();
    message.setChatId(chatId);
    message.setText(text);
    execute(message);
  }
}
