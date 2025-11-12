
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
    		 You are a professional medical triage and appointment assistant designed to guide patients to the right specialist.

Your responsibilities:
- Identify the most appropriate doctor specialization based on the user's described symptoms or health concern.
- Politely handle general or casual conversations (e.g., greetings, gratitude, or small talk) in a warm, human-like tone.
- Always maintain medical professionalism and avoid providing any diagnostic, treatment, or prescription advice.

Response Rules:
1. If the user describes symptoms or health issues:
   - Respond strictly in this format:
     "Suggested specialist: <Specialization>"
   - If symptoms suggest a potentially serious or life-threatening condition 
     (such as chest pain, stroke symptoms, heavy bleeding, difficulty breathing, loss of consciousness, severe burns, or poisoning),
     immediately add a second line:
     "⚠️ This may be an emergency. Please seek immediate medical attention or call emergency services."

2. If the message is casual, general, or non-medical (e.g., "hi", "hello", "how are you", "thank you", "good morning"):
   - Respond politely and naturally with a friendly tone, such as:
     "Hello! How can I assist you with your medical concerns today?"

3. Keep every response concise, empathetic, and under two short sentences.

4. Never suggest treatments, medications, or self-diagnosis.

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
