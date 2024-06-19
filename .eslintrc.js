module.exports = {
  env: {
    node: true,
  },
  extends: ['eslint:recommended', 'plugin:vue/vue3-recommended', '@vue/eslint-config-typescript', '@vue/prettier'],
  parserOptions: {
    parser: '@typescript-eslint/parser',
  },
  rules: {
    'vue/no-reserved-component-names': 'off',
    quotes: ['error', 'single', { avoidEscape: true }],
    '@typescript-eslint/no-unused-vars': ['error'],
    'prettier/prettier': [
      'error',
      {
        endOfLine: 'auto',
      },
    ],
  },
};
