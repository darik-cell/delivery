import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TreeWalker {

  // Названия файлов и директорий, которые нужно исключить из обхода
  private static final Set<String> EXCLUDED = new HashSet<>(Arrays.asList(
          "TreeWalker.java", "filetree.txt", ".gitignore", ".gitattributes", "target", ".mvn", ".idea"
  ));

  public static void main(String[] args) {
    // Добавить свои исключения (если нужно)
    if (args.length > 0) {
      EXCLUDED.addAll(Arrays.asList(args));
    }

    File projectRoot = new File("."); // Корень проекта
    File outputFile = new File("filetree.txt");

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
      walkDirectory(projectRoot, writer, "", projectRoot.getCanonicalPath());
      System.out.println("Файл filetree.txt успешно создан.");
    } catch (IOException e) {
      System.err.println("Ошибка при записи дерева файлов: " + e.getMessage());
    }
  }

  /**
   * Рекурсивный обход директории и запись структуры в файл.
   *
   * @param dir         Текущая директория
   * @param writer      Буфер для записи в файл
   * @param indent      Отступ для визуализации уровня вложенности
   * @param rootPath    Канонический путь корня для корректной работы
   * @throws IOException Исключение ввода/вывода
   */
  private static void walkDirectory(File dir, BufferedWriter writer, String indent, String rootPath) throws IOException {
    if (!dir.isDirectory()) return;

    File[] files = dir.listFiles();
    if (files == null) return;

    for (File file : files) {
      String relativePath = file.getCanonicalPath().replace(rootPath, "").replace(File.separator, "/");
      String fileName = file.getName();

      // Пропуск исключенных файлов и директорий
      if (EXCLUDED.contains(fileName) || isExcludedByPath(relativePath)) {
        continue;
      }

      writer.write(indent + (file.isDirectory() ? "[D] " : "[F] ") + fileName);
      writer.newLine();

      // Рекурсивный вызов для директорий
      if (file.isDirectory()) {
        walkDirectory(file, writer, indent + "  ", rootPath);
      }
    }
  }

  /**
   * Проверяет, исключен ли файл/директория по относительному пути.
   *
   * @param relativePath Относительный путь файла/директории
   * @return true, если исключен
   */
  private static boolean isExcludedByPath(String relativePath) {
    for (String excluded : EXCLUDED) {
      if (relativePath.contains(excluded)) {
        return true;
      }
    }
    return false;
  }
}
