/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module plugins/search
 */
import type { ICreate, ISelectionRange } from '../../../types';
export declare function wrapRangesTextsInTmpSpan(rng: ISelectionRange, restRanges: ISelectionRange[], ci: ICreate, root: HTMLElement): void;
export declare function getSelectionWrappers(root: HTMLElement): HTMLElement[];
export declare function clearSelectionWrappers(root: HTMLElement): void;
export declare function clearSelectionWrappersFromHTML(root: string): string;
export declare function isSelectionWrapper(node: unknown): boolean;
