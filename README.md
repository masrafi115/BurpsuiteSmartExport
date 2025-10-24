# BurpSuiteSmartExport

A one-click Burp Suite extension (Java) that exports all HTTP history to a formatted text file so that it could be passed to a LLM.

---

## Requirements

* Java (JDK) installed (compatible with your Gradle toolchain)
* Burp Suite (Community or Professional)

---

## Installation

1. Clone this repository:

```bash
git clone https://github.com/ojasmaheshwari/BurpsuiteSmartExport.git
cd BurpsuiteSmartExport
```

2. Build the JAR using the included Gradle wrapper:

* On UNIX-based systems (Linux, macOS):

```bash
./gradlew jar
```

* On Windows:

```powershell
gradlew jar
```

3. After the build completes you'll find a `.jar` in `build/libs/`.

---

## Load the extension into Burp Suite

1. Open Burp Suite.
2. Go to **Extensions** → **Add**.
3. For **Extension type**, choose **Java**.
4. Select the generated `.jar` file from `build/libs/` and click **Next**.
5. The extension should load and appear in the Extensions list.

---

## How to use

1. In Burp's **Proxy** tab, right-click any request in the HTTP history list.
2. From the context menu choose **Extensions** → **Smart Export** → **Save .txt**.
3. The full text file will be written to one of the following locations (the folder is created if it does not exist):

* **Windows:**

```
C:\Users\<you>\BurpSmartSearch\full_history_<random>.txt
```

* **Linux / macOS:**

```
~/BurpSmartSearch/full_history_<random>.txt
```

The `<random>` suffix is added to avoid overwriting previous exports.

---

## Troubleshooting

* If the extension fails to load, verify you built the JAR successfully and that your Java version matches the Gradle toolchain settings.
* If files are not being created, ensure the running Burp process has write permissions for the target folder.
* Check output and errors in Burp's **Extensions** tab for any error messages or stack traces produced by the extension.

---

## Contributing

Contributions, bug reports and feature requests are welcome. Open an issue or submit a pull request with proposed changes.

---

## License

Specify your preferred license here (e.g., MIT). If you don't want to include a license file in this repo, note the intended license in this section.

---

## Contact

Created by Ojas Maheshwari. For questions or help, please open an issue on the repository.
