/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module ui/form/inputs
 */

import type { IUITextArea, IViewBased } from '../../../../../types';
import { UIInput } from '../../../../../core/ui/form/inputs/input/input';
export declare class UITextArea extends UIInput implements IUITextArea {
    /** @override */
    className(): string;
    /** @override */
    static defaultState: IUITextArea['state'];
    nativeInput: HTMLTextAreaElement;
    /** @override */
    state: IUITextArea['state'];
    /** @override */
    protected createContainer(options: this['state']): HTMLElement;
    constructor(jodit: IViewBased, state: Partial<IUITextArea['state']>);
}