import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileTreeWalker {

  public static void main(String[] args) {
    Path startDir = Paths.get("."); // Текущая директория
    Path outputDir = Paths.get("tmp"); // Директория для записи результата
    Path outputFile = outputDir.resolve("res.txt"); // Файл результата

    // Создаем директорию tmp, если она еще не существует
    try {
      Files.createDirectories(outputDir);
    } catch (IOException e) {
      System.err.println("Не удалось создать директорию tmp: " + e.getMessage());
      return;
    }

    // Очищаем файл res.txt перед записью, если он уже существует
    try {
      Files.write(outputFile, new byte[0], StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
    } catch (IOException e) {
      System.err.println("Не удалось инициализировать файл res.txt: " + e.getMessage());
      return;
    }

    try {
      Files.walkFileTree(startDir, new SimpleFileVisitor<Path>() {

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
          // Пропускаем директорию tmp
          if (dir.equals(outputDir)) {
            return FileVisitResult.SKIP_SUBTREE;
          }
          return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
          // Пропускаем файл res.txt и FileTreeWalker.java
          String fileName = file.getFileName().toString();
          if (fileName.equals("res.txt") || fileName.equals("FileTreeWalker.java")) {
            return FileVisitResult.CONTINUE;
          }

          // Печатаем название файла и его содержимое в файл res.txt
          StringBuilder content = new StringBuilder("File: " + file.getFileName() + "\n");

          // Читаем содержимое файла
          List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
          lines.forEach(line -> content.append(line).append("\n"));
          content.append("\n"); // Разделитель между файлами

          // Записываем в res.txt
          Files.write(outputFile, content.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

          return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
          System.err.println("Ошибка доступа к файлу: " + file + " - " + exc.getMessage());
          return FileVisitResult.CONTINUE;
        }
      });
    } catch (IOException e) {
      System.err.println("Ошибка обхода файловой системы: " + e.getMessage());
    }
  }
}
