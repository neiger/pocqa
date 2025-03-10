# PoCQA - QA Automation Proof of Concept

## 📌 Setup Instructions

### 1️⃣ Clone the repository
```
git clone https://github.com/neiger/pocqa.git
cd pocqa
```
### 2️⃣ Update Maven dependencies
```
mvn clean install
```

### 3️⃣ Set proper parameters in config.xml
```
File location: src/test/resources/config.xml
```

### 4️⃣ Launch Appium
```
appium
```

### 5️⃣ Execute the tests
Using IntelliJ: Run test classes directly
Using Maven:
```
mvn clean test
```