/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module plugins/enter
 */
import type { HTMLTagNames, IJodit, Nullable } from '../../../types';
/**
 * Insert default paragraph
 * @private
 */
export declare function insertParagraph(editor: IJodit, fake: Nullable<Text>, wrapperTag: HTMLTagNames, style?: CSSStyleDeclaration): HTMLElement;