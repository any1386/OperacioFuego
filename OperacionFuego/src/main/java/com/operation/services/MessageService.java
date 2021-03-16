package com.operation.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.operation.services.exception.MessageException;

@Service
public class MessageService {
	public List<String> getMsgSentence(List<List<String>> listMessage) {

		
        List<String> lisSentences = listMessage.stream().filter(lm -> lm != null).distinct().
        		flatMap(List::stream).
        		collect(Collectors.toList());
        lisSentences.remove("");
		return lisSentences;
	}

	public void removeSpace(List<List<String>> listMessage, int gapSize) {

		int size = 0;
		for (int i = 0; i < listMessage.size(); i++) {
			size = listMessage.get(i).size();
			listMessage.set(i, listMessage.get(i).subList(size - gapSize, size));
		}
	}

	public String completeMessage(List<List<String>> listMessage) {

		String phrase = "";
		for (List<String> message : listMessage) {

			if (message.size() > 0 && !message.get(0).equals("")) {
				phrase = (message.size() == 1) ? message.get(0) : message.get(0) + " ";
				listMessage.stream().forEach(s -> s.remove(0));
				return phrase + completeMessage(listMessage);
			}
		}
		return "";
	}

	public boolean validateMessageSentences(List<String> sentences, String message) {
		List<String> msg = Arrays.stream(message.split(" ")).collect(Collectors.toList());
		Collections.sort(sentences);
		Collections.sort(msg);
		return Arrays.equals(sentences.toArray(), msg.toArray());
	}

	public String getMessage(List<List<String>> listMessage) throws MessageException {

		List<String> msgPhrases = getMsgSentence(listMessage);
		if (isSizeMessageFault(listMessage, msgPhrases.size()))
			throw new MessageException("longitud de mensaje incorrecto");

		removeSpace(listMessage, msgPhrases.size());
		String message = completeMessage(listMessage);
		if (!validateMessageSentences(msgPhrases, message))
			throw new MessageException("No se puede conocer el mensaje");

		return message;
	}

	public boolean isSizeMessageFault(List<List<String>> messages, int size) {
		Optional<List<String>> listMess = messages.stream().filter(lm -> lm != null && lm.size() >= size).findFirst();
		if (listMess.isPresent()) {
			return true;
		}

		return false;
	}

}
