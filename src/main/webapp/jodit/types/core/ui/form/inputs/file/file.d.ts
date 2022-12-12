/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module ui/form/inputs
 */

import type { IUIInput, IViewBased } from '../../../../../types';
import { UIInput } from '../../../../../core/ui/form/inputs/input/input';
export declare class UIFileInput extends UIInput {
    private button;
    state: UIInput['state'] & {
        onlyImages: boolean;
    };
    /** @override */
    className(): string;
    protected createContainer(options: Partial<this['state']>): HTMLElement;
    protected createNativeInput(options: Partial<this['state']>): IUIInput['nativeInput'];
    constructor(jodit: IViewBased, options: Partial<UIFileInput['state']>);
}
