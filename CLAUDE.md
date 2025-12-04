CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a personal Advent of Code solutions repository containing solutions across multiple years (2022-2025) and programming languages. Solutions are organized by year, then language, then day.

### Directory Structure

```
advent-of-code/
├── 2022/, 2023/, 2024/, 2025/    # Year directories with solutions
├── input/                         # Problem input files (organized by year/day)
├── lib/                          # JAR dependencies (commons-lang3, lombok)
├── out/                          # IntelliJ build artifacts
├── .idea/                        # IntelliJ IDEA configuration
└── go.mod                        # Go module definition
```

## Languages and Structure

### Java Solutions (2022, 2024-2025)
- **Location**: `{year}/java/src/com/paul/day{X}/`
- **Package Structure**: One package per day with solution classes
- **Naming Pattern**: Each day has:
  - `{SolutionName}.java` - Part 1 solution
  - `{SolutionName}Part2.java` - Part 2 solution
  - Supporting utility classes as needed
- **Compilation**: Automatic via IntelliJ IDEA
- **Build Artifacts**: Compiled to `out/production/advent-of-code/`

### Go Solutions (2023)
- **Location**: `2023/day-{X}/`
- **Structure**: Solution file (`day{X}.go`) paired with test file (`day{X}_test.go`)
- **Testing**: Native Go testing package with table-driven tests
- **Module**: Go 1.21.3 with no external dependencies (standard library only)

### Input Files
- **Location**: `input/{year}/day{X}/{SolutionName}.txt`
- **Test Data**: Test inputs typically stored alongside or in the same directory
- **Path Handling**: Solutions reference input files with relative paths from project root

## Building and Running

### Java Solutions
1. **IntelliJ**: Open the project (already configured)
2. **Build**: Automatic compilation via IntelliJ
3. **Run**: Right-click solution class → Run, or use `Ctrl+Shift+F10`
4. **Output**: Results printed to console

### Go Solutions
```bash
cd 2023/day-{X}/
go test -v                    # Run tests
go run day{X}.go             # Run solution (if main function exists)
```

## Dependencies and Libraries

### Java Libraries
- **commons-lang3-3.17.0.jar**: Apache Commons Lang utilities
- **lombok.jar**: Annotation processor for reducing boilerplate
- **Configured in**: `.idea/libraries/commons_lang3_3_17_0.xml`
- **Annotation Processing**: Enabled in `.idea/compiler.xml`

### Go Dependencies
- None (standard library only)

## Key Development Notes

### Java Solution Pattern
```java
public class SolutionName {
    public static void main(String[] args) {
        String filePath = "input/2024/dayX/SolutionName.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read and process input
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Result: " + answer);
    }
}
```

- Solutions are self-contained with main methods
- Input files are read from the `input/` directory
- Results are printed to console
- Each day gets its own package under `com.paul.dayX`

### Go Solution Pattern
- Table-driven tests in `day{X}_test.go`
- Test data files (sample.txt, whole.txt, etc.) in the same directory
- Functions exported (capitalized) for testing
- Use only standard library

## Project Configuration

### IntelliJ IDEA Setup
- **Module File**: `.idea/advent-of-code.iml` (defines source folders and dependencies)
- **Compiler Settings**: `.idea/compiler.xml` (annotation processing enabled)
- **Libraries**: `.idea/libraries/` (library definitions)
- **Version Control**: `.idea/vcs.xml` (Git configured)
- **Ignored in Git**: Only `.idea/workspace.xml` (editor state), everything else committed

### Git Status
- Active branch: `main`
- Modified files tracked with `.idea/` configuration files
- Build artifacts in `out/` (not explicitly ignored but can be added)

## Common Tasks

### Adding a New Java Day
1. Create package: `{year}/java/src/com/paul/day{X}/`
2. Create solution class with main method
3. Add input file to `input/{year}/dayX/{SolutionName}.txt`
4. Run via IntelliJ

### Adding a New Go Day
1. Create directory: `2023/day-{X}/`
2. Create `day{X}.go` with solution functions
3. Create `day{X}_test.go` with table-driven tests
4. Add test data files (sample.txt, whole.txt, etc.)
5. Run tests with `go test -v`

### Reading Input Files
- Always use relative paths from project root
- Format: `input/{year}/dayX/{filename}.txt`
- Test files optionally stored with "Test" suffix in filename
