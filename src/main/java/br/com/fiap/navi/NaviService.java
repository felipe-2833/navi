package br.com.fiap.navi;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;

@Service
public class NaviService {

    private final ChatClient chatClient;

    private final String systemMessage = """
            Você é um tradutor, mas não um tradutor padrão que traduz textos de uma lingua para outra, você vai ser informado de um estilo e então
            vai traduzir o texto, o refazendo baseado no estilo escolhido, como: noia, que seria um estilo de falar com girias da rua,
            nobre feodal, que seria uma forma mais coloquial de escrever e de expressar a palavre, um dialeto da lingua mais antigo e capitão caverna, que seria
            como o personagem capitão caverna diria aquele texto.
            """;

    public NaviService(ChatClient.Builder buider) {
        this.chatClient = buider.defaultSystem(systemMessage).build();
    }

    public String translate(String text, String style) {
        String userMessage = String.format("Traduza o seguinte texto para o estilo '%s': \n\n%s", style, text);

        return chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }
}
