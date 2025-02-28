package com.epita.creeps.AI_manager.Memory;

import com.epita.creeps.tool.GraphKeeper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JSON_Memory {

    public static String toJson(GraphKeeper keeper) {
        if (keeper == null)
            return null;
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(keeper);
        } catch (JsonProcessingException e) {
            System.err.println("[JsonMemory]{toJson} :The mapper failed to map the keeper");
            throw new RuntimeException(e);
        }
    }

    public static GraphKeeper fromJson(String json) {
        if (json == null)
            return null;
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, GraphKeeper.class);
        } catch (IOException e) {
            System.err.println("[JsonMemory]{fromJson} :The mapper failed to map the keeper");
            return null;
        }
    }

    public static boolean write(String text, String path) {
        Path file = Paths.get(path);
        if (file == null) {
            System.err.println("[JsonMemory]{write -> text} :Could not open the file");
            return false;
        }

        List<String> lines = new ArrayList<>();
        lines.add(text);

        try {
            Files.write(file, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("[JsonMemory]{write -> text} :The write methods didn't work,\n -> please check the arguments");
            return false;
        }
        return true;
    }

    public static boolean write(GraphKeeper keeper, String path) {
        String json = toJson(keeper);
        if (json == null)
            System.err.println("[JsonMemory]{write -> keeper} :The toJson methods didn't work,\n -> please check the arguments");
        return write(json, path);
    }

    public static String read_text(String path) {
        Path file = Paths.get(path);
        if (file == null) {
            System.err.println("[JsonMemory]{read -> text} :Could not open the file");
            return null;
        }

        try {
            List<String> lines = Files.readAllLines(file);
            String text = String.join("\n", lines);
            return text;
        } catch (IOException e) {
            System.err.println("[JsonMemory]{read -> text} :The read methods didn't work,\n -> please check the arguments");
            return null;
        }
    }

    public static GraphKeeper read_graph(String path) {
        return fromJson(read_text(path));
    }
}
