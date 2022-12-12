/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module storage/memory
 */
import type { IStorage, StorageValueType } from '../../../types';
export declare class MemoryStorageProvider<T = StorageValueType> implements IStorage<T> {
    private data;
    set(key: string, value: T): IStorage<T>;
    delete(key: string): IStorage<T>;
    get<R = T>(key: string): R | void;
    exists(key: string): boolean;
    clear(): IStorage<T>;
}
