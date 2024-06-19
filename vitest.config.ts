/// <reference types="vitest" />

import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import path from 'path';

export default defineConfig({
    plugins: [vue()],
    resolve: {
        alias: [{ find: '@', replacement: path.resolve(__dirname, 'src/main/webapp/osca') }],
    },
    test: {
        reporters: ['json', 'verbose', 'vitest-sonar-reporter'],
        outputFile: {
            'vitest-sonar-reporter': 'target/test-results/TESTS-results-sonar.xml',
        },
        globals: true,
        logHeapUsage: true,
        minThreads: 1,
        maxThreads: 2,
        environment: 'jsdom',
        cache: false,
        include: ['src/main/webapp/**/__tests__/**/*.(spec|test).(ts|tsx)'],
        exclude: ['node_modules', 'src/main/webapp/**/__tests__/integration/**/*.spec.ts'],
        coverage: {
            statements: 20,
            branches: 20,
            functions: 20,
            lines: 20,
            clean: true,
            exclude: ['src/main/webapp/**/*.component.ts'],
            provider: 'istanbul',
            reportsDirectory: 'target/test-results/',
            reporter: ['html', 'json-summary', 'text', 'text-summary', 'lcov', 'clover'],
            watermarks: {
                statements: [20, 20],
                branches: [20, 20],
                functions: [20, 20],
                lines: [20, 20],
            },
        },
    },
});
