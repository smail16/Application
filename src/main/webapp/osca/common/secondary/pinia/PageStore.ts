// projectPageStore.ts
import { defineStore } from 'pinia';
import { Page } from '@/common/domain/Page';

interface ProjectPageState {
    activePageDetails: Page | null;
}

export const usePageStore = defineStore('pages', {
    state: (): ProjectPageState => ({
        activePageDetails: null,
    }),
    actions: {
        setActivePageDetail(page: Page) {
            this.activePageDetails = page;
        },
    },
});
