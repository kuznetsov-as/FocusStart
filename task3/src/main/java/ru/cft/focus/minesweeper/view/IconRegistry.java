package ru.cft.focus.minesweeper.view;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class IconRegistry {
    private final Map<String, ImageIcon> cellIconMap = new HashMap<>();

    IconRegistry() {

        cellIconMap.put("restart", new ImageIcon(
                IconRegistry.class.getResource("/images/restart.png")));

        cellIconMap.put("pentagram", new ImageIcon(
                IconRegistry.class.getResource("/images/flag.png")));

        cellIconMap.put("question", new ImageIcon(
                IconRegistry.class.getResource("/images/question.png")));

        cellIconMap.put("grave", new ImageIcon(
                IconRegistry.class.getResource("/images/grave.png")));

        cellIconMap.put("zombi", new ImageIcon(
                IconRegistry.class.getResource("/images/zombi.png")));

        cellIconMap.put("open grave", new ImageIcon(
                IconRegistry.class.getResource("/images/open_grave.png")));

        cellIconMap.put("open grave with the number 1", new ImageIcon(
                IconRegistry.class.getResource("/images/grave-1.png")));

        cellIconMap.put("open grave with the number 2", new ImageIcon(
                IconRegistry.class.getResource("/images/grave-2.png")));

        cellIconMap.put("open grave with the number 3", new ImageIcon(
                IconRegistry.class.getResource("/images/grave-3.png")));

        cellIconMap.put("open grave with the number 4", new ImageIcon(
                IconRegistry.class.getResource("/images/grave-4.png")));

        cellIconMap.put("open grave with the number 5", new ImageIcon(
                IconRegistry.class.getResource("/images/grave-5.png")));

        cellIconMap.put("open grave with the number 6", new ImageIcon(
                IconRegistry.class.getResource("/images/grave-6.png")));

        cellIconMap.put("open grave with the number 7", new ImageIcon(
                IconRegistry.class.getResource("/images/grave-7.png")));

        cellIconMap.put("open grave with the number 8", new ImageIcon(
                IconRegistry.class.getResource("/images/grave-8.png")));
    }


    Optional<ImageIcon> getImageForCell(String code) {
        return Optional.ofNullable(cellIconMap.get(code));
    }
}