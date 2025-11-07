
package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "http://localhost:3000") // allow your React app
public class OpenAIService {

  @Value("${gemini.api.key}")
  private String apiKey;

  @PostMapping("/chat")
  public String chat(@RequestBody ChatRequest body) {
    try {
      String prompt = """
    		  You are a professional medical triage assistant. 
    		  Analyze the user's message and suggest the most appropriate doctor specialization.

    		  Guidelines:
    		  1. Always begin with: "Suggested specialist: <Specialization>"
    		  2. If the symptoms indicate a potentially serious or life-threatening condition 
    		     (for example: chest pain, stroke, heavy bleeding, difficulty breathing, loss of consciousness, severe burns, or poisoning),
    		     add a second line: "⚠️ This may be an emergency. Please seek immediate medical attention or call emergency services."
    		  3. Keep your answer concise — no more than 2 short sentences.
    		  4. Do not provide any treatment or medication advice.
    		  5. If the issue is general or unclear, suggest "General Physician."

    		  User message: %s
    		  """.formatted(body.message());

      Client client = Client.builder().apiKey(apiKey).build();
      GenerateContentResponse resp =
          client.models.generateContent("gemini-2.5-flash", prompt, null);

      return resp.text();
    } catch (Exception e) {
      return "Sorry, I couldn't process that right now.";
    }
  }

  
  public record ChatRequest(String message) {}
}
